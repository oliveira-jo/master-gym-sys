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
import { EnrollmentModalityRequest } from "../../../core/model/enrollment-modality-request.model";
import { EnrollmentModalityFormComponent } from "../enrollment-modality-form/enrollment-modality-form.component";
import { EnrollmentModalityResponse } from "../../../core/model/enrollment-modality-response.model";
import { StudentRequest } from "../../../core/model/student-request.model";

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

  @Input() enrollment?: EnrollmentResponse;

  @Output() close = new EventEmitter<boolean>();

  private enrollmentService = inject(EnrollmentService);

  private studentService = inject(StudentService);

  private fb = inject(NonNullableFormBuilder);

  students: StudentResponse[] = [];

  page = new PageableRequest(0, 500, 'id');

  days = DAYS;

  modalOpen = false;

  maxEnrollmenteModality = false;

  selectedEnrollmentModality!: EnrollmentModalityResponse;

  enrollmentModalities: EnrollmentModalityResponse[] = [];

  form = this.fb.group({
    id: [0, Validators.required],
    enrollmentDate: ['', Validators.required],
    dueDay: [0, Validators.required],
    closingDate: ['', Validators.required],
    studentId: this.fb.control<number | null>(null, Validators.required),
  });


  ngOnInit() {
    if (this.enrollment) {
      this.form.patchValue({
        ...this.enrollment,
      });
    }
    this.loadStudents();
  }

  loadStudents(): void {
    const filter: StudentFilterRequest = { name: '', email: '' };

    this.studentService
      .findAll(filter, this.page)
      .subscribe({
        next: page => {
          this.students = page.content;
        },
        error: err => console.error(err)
      });

  }

  save(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    const request: EnrollmentRequest = this.form.getRawValue();
    //convert data for ISO
    request.enrollmentDate = DateUtils.toIso(request.enrollmentDate);

    if (this.enrollment) {
      this.enrollmentService
        .update(this.enrollment.id, request)
        .subscribe(() => this.close.emit(true));
    } else {
      this.enrollmentService
        .create(request)
        .subscribe(() => this.close.emit(true));
    }

  }

  ngOnChanges(): void {
    if (!this.enrollment) {
      this.form.reset();
      return;
    }
    this.form.patchValue({
      enrollmentDate: this.enrollment.enrollmentDate,
      dueDay: this.enrollment.dueDay,
      closingDate: this.enrollment.closingDate,
      studentId: this.enrollment.student.id,
    });
  }

  cancel() {
    this.close.emit(false);
  }

  deleteEnrollmentModality(obj: EnrollmentModalityResponse): void {
    this.enrollmentModalities = this.enrollmentModalities.filter(
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
      this.enrollmentModalities.push(result);
    }
  }

}