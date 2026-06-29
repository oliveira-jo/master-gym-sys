import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Inject, Input, Output } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { EnrollmentResponse } from '../../../core/model/enrollment-response.model';
import { EnrollmentService } from '../../../core/service/enrollment.service';
import { EnrollmentRequest } from '../../../core/model/enrollment-request.model';

@Component({
  selector: 'app-enrollment-form',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './enrollment-form.component.html',
  styleUrl: './enrollment-form.component.css'
})
export class EnrollmentFormComponent {

  @Input() enrollment?: EnrollmentResponse;
  @Output() close = new EventEmitter<boolean>();

  private fb = Inject(NonNullableFormBuilder);
  private enrollmentService = inject(EnrollmentService);

  form = this.fb.group({
    enrollmentDate: ['', Validators.required],
    dueDay: ['', Validators.required],
    closingDate: ['', Validators.required],
    studentId: ['', Validators.required],
  });

  ngOnInit() {
    if (this.enrollment) {
      this.form.patchValue({
        ...this.enrollment,
      });
    }
  }

  save(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const request: EnrollmentRequest = this.form.getRawValue();


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
      studentId: this.enrollment.studentId,
    });
  }

  cancel() {
    this.close.emit(false);
  }
}