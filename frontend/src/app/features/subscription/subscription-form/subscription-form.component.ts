import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Input, OnInit, Output } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { SubscriptionResponse } from '../../../core/model/subscription-response.model';
import { SubscriptionService } from '../../../core/service/subscription.service';
import { SubscriptionRequest } from '../../../core/model/subscription-request.model';
import { ModalityResponse } from '../../../core/model/modality-response.model';
import { ModalityService } from '../../../core/service/modality.service';
import { PageableRequest } from '../../../core/model/page/pageable-request.model';

@Component({
  selector: 'app-subscription-form',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './subscription-form.component.html',
  styleUrl: './subscription-form.component.css'
})
export class SubscriptionFormComponent {

  @Input() subscription?: SubscriptionResponse;
  @Output() close = new EventEmitter<boolean>();

  private fb = inject(NonNullableFormBuilder);
  private subscriptionService = inject(SubscriptionService);

  form = this.fb.group({
    name: ['', Validators.required],
    modalityName: ['', Validators.required],
    price: [0.0, Validators.required],
    active: [true, Validators.required],
  });

  private readonly modalityService = inject(ModalityService);

  modalities: ModalityResponse[] = [];
  page = new PageableRequest(0, 100, 'name');

  ngOnInit() {

    if (this.subscription) {
      this.form.patchValue({
        name: this.subscription.name,
        modalityName: this.subscription.modality?.name,
        price: this.subscription.price,
        active: this.subscription.active
      });
    }

    this.loadModalities();

  }

  loadModalities(): void {
    this.modalityService
      .findAll(this.page)
      .subscribe({
        next: page => {
          this.modalities = page.content;

        },
        error: err => console.error(err)

      });
  }

  save(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const request: SubscriptionRequest = this.form.getRawValue();

    if (this.subscription) {
      this.subscriptionService
        .update(this.subscription.id, request)
        .subscribe(() => this.close.emit(true));

    } else {
      this.subscriptionService
        .create(request)
        .subscribe(() => this.close.emit(true));

    }

  }

  ngOnChanges(): void {
    if (!this.subscription) {
      this.form.reset();
      return;
    }

    this.form.patchValue({
      name: this.subscription.name,
    });
  }

  cancel() {
    this.close.emit(false);
  }
}