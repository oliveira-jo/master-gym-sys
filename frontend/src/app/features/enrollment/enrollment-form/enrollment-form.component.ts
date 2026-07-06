import { Component, EventEmitter, inject, Input, Output } from "@angular/core";
import { EnrollmentResponse } from "../../../core/model/enrollment-response.model";
import { EnrollmentService } from "../../../core/service/enrollment.service";
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from "@angular/forms";
import { CommonModule } from "@angular/common";
import { EnrollmentRequest } from "../../../core/model/enrollment-request.model";
import { StudentService } from "../../../core/service/student.service";
import { StudentResponse } from "../../../core/model/student-response.model";
import { PageableRequest } from "../../../core/model/page/pageable-request.model";
import { StudentFilterRequest } from "../../../core/model/student-filter.model";
import { DateUtils } from "../../../core/utils/DateUtils";
import { DAYS } from "../../../core/constant/days";
import { EnrollmentModalityFormComponent } from "../enrollment-modality-form/enrollment-modality-form.component";
import { EnrollmentModalityResponse } from "../../../core/model/enrollment-modality-response.model";

import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-enrollment-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    EnrollmentModalityFormComponent
  ],
  templateUrl: './enrollment-form.component.html',
})
export class EnrollmentFormComponent {

  @Output() close = new EventEmitter<boolean>();

  private fb = inject(NonNullableFormBuilder);
  private enrollmentService = inject(EnrollmentService);
  private studentService = inject(StudentService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  enrollmentModalitiesResponse: EnrollmentModalityResponse[] = [];

  selectedEnrollmentModality!: EnrollmentModalityResponse;

  students: StudentResponse[] = [];

  enrollment?: EnrollmentResponse;

  isEdit = false;

  page = new PageableRequest(0, 500, 'id');

  maxEnrollmenteModality = false;

  modalOpen = false;

  days = DAYS;

  form = this.fb.group({
    id: this.fb.control<number | null>(null),
    enrollmentDate: this.fb.control('', Validators.required),
    closingDate: this.fb.control(''),
    dueDay: this.fb.control<number | null>(null, Validators.required),
    studentId: this.fb.control<number | null>(null, Validators.required),
  });

  ngOnInit() {

    this.loadStudents();

    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.isEdit = true;
      this.loadEnrollment(+id);
    }

  }

  loadEnrollment(id: number): void {
    this.enrollmentService.getById(id)
      .subscribe(enrollment => {

        // Log for see what api retur in payload
        // console.log("-----> ", enrollment);

        this.enrollment = enrollment;

        this.form.patchValue({
          id: enrollment.id,
          enrollmentDate: enrollment.enrollmentDate,
          closingDate: enrollment.closingDate,
          dueDay: enrollment.dueDay,
          studentId: enrollment.student.id
        });

        this.enrollmentModalitiesResponse = [
          ...enrollment.enrollmentModalities
        ];
      });
  }

  loadStudents(): void {
    const filter: StudentFilterRequest = { name: '', email: '' };

    this.studentService
      .findAll(filter, this.page)
      .subscribe({
        next: page => {
          this.students = page.content;

          const id = this.route.snapshot.paramMap.get('id');
          if (id) {
            this.loadEnrollment(+id);
          }
        },
        error: err => console.error(err)
      });

  }

  save(): void {

    //Logic
    if (this.form.invalid) {
      console.log("this.form.invalid ? ", this.form.invalid)
      this.form.markAllAsTouched();
      return;
    }

    const request: EnrollmentRequest = {
      ...this.form.getRawValue(),
      enrollmentModalities: this.enrollmentModalitiesResponse.map(m => ({
        modalityId: m.modality.id,
        graduationId: m.graduation.id,
        subscriptionId: m.subscription.id,
        startDate: m.startDate,
        endDate: m.endDate
      }))
    };

    request.enrollmentDate = DateUtils.toIso(request.enrollmentDate!);

    if (request.closingDate) {
      request.closingDate = DateUtils.toIso(request.closingDate);
    }

    //add and convert EnrollmentModality to Enrollment
    request.enrollmentModalities = this.enrollmentModalitiesResponse.map(m => ({
      modalityId: m.modality.id,
      graduationId: m.graduation.id,
      subscriptionId: m.subscription.id,
      startDate: m.startDate,
      endDate: m.endDate
    }));

    // Logge
    // console.log(" Enrollment Modality data ", request.enrollmentModalities);

    if (this.isEdit) {
      this.enrollmentService
        .update(this.enrollment!.id, request)
        .subscribe(() => {
          this.router.navigate(['/matriculas']);
        });

    } else {
      this.enrollmentService
        .create(request)
        .subscribe(() => {
          this.router.navigate(['/matriculas']);
        });
    }

  }

  cancel() {
    this.close.emit(false);
  }

  backEnrollmentList() {
    this.router.navigate(['/matriculas']);
  }

  deleteEnrollmentModality(obj: EnrollmentModalityResponse): void {
    this.enrollmentModalitiesResponse = this.enrollmentModalitiesResponse.filter(
      item => item !== obj
    );
    this.maxEnrollmenteModality = false;
  }

  openModal() {
    this.modalOpen = true;

    this.selectedEnrollmentModality = {
      modality: null!,
      graduation: null!,
      subscription: null!,
      startDate: '',
      endDate: ''
    };

    this.maxEnrollmenteModality = true;

  }

  closeModal(result?: EnrollmentModalityResponse) {
    this.modalOpen = false;
    if (result) {
      this.enrollmentModalitiesResponse.push(result);
    }
  }

}