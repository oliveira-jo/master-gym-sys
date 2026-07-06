import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Input, Output, } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators, } from '@angular/forms';
import { EnrollmentModalityRequest } from '../../../core/model/request/enrollment-modality-request.model';
import { ModalityService } from '../../../core/service/modality.service';
import { SubscriptionService } from '../../../core/service/subscription.service';
import { GraduationService } from '../../../core/service/graduation.service';
import { ModalityResponse } from '../../../core/model/response/modality-response.model';
import { PageableRequest } from '../../../core/model/page/pageable-request.model';
import { GraduationResponse } from '../../../core/model/response/graduation-response.model';
import { SubscriptionResponse } from '../../../core/model/response/subscription-response.model';
import { EnrollmentModalityResponse } from '../../../core/model/response/enrollment-modality-response.model';

@Component({
  selector: 'app-enrollment-modality-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './enrollment-modality-form.component.html',
  styleUrl: './enrollment-modality-form.component.css'
})
export class EnrollmentModalityFormComponent {

  @Input()
  enrollmentModality!: EnrollmentModalityRequest;

  @Output()
  close = new EventEmitter<EnrollmentModalityResponse | undefined>();

  private fb = inject(NonNullableFormBuilder);

  form = this.fb.group({
    modality: this.fb.control<ModalityResponse | null>(null, Validators.required),
    graduation: this.fb.control<GraduationResponse | null>(null, Validators.required),
    subscription: this.fb.control<SubscriptionResponse | null>(null, Validators.required),
    startDate: this.fb.control<string | null>(null, Validators.required),
    endDate: this.fb.control<string | null>(null, Validators.required),

  });

  private readonly modalityService = inject(ModalityService);
  private readonly graduationService = inject(GraduationService);
  private readonly subscriptionService = inject(SubscriptionService);

  modalities: ModalityResponse[] = [];
  graduations: GraduationResponse[] = [];
  subscriptions: SubscriptionResponse[] = [];

  page = new PageableRequest(0, 100, 'id');

  ngOnInit(): void {

    this.loadDatas();

    if (this.enrollmentModality) {
      this.form.patchValue({
        ...this.enrollmentModality,
      });
    }

  }

  loadDatas(): void {

    this.modalityService
      .findAll(this.page)
      .subscribe({
        next: page => {
          this.modalities = page.content;
        },
        error: err => console.error(err)
      });

    this.graduationService
      .findAll(this.page)
      .subscribe({
        next: page => {
          this.graduations = page.content;
        },
        error: err => console.error(err)
      });

    this.subscriptionService
      .findAll(this.page)
      .subscribe({
        next: page => {
          this.subscriptions = page.content;
        },
        error: err => console.error(err)
      });

  }

  save() {

    const response: EnrollmentModalityResponse = {
      modality: this.form.value.modality!,
      graduation: this.form.value.graduation!,
      subscription: this.form.value.subscription!,
      startDate: this.form.value.startDate!,
      endDate: this.form.value.endDate!
    };

    this.close.emit(response);

  }

  cancel() {
    this.close.emit();
  }

}
