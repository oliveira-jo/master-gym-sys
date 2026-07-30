import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { PaymentResponse } from '../../../../core/model/response/payment-response-model';
import { PaymentService } from '../../../../core/service/payment.service';


@Component({
  selector: 'app-payment-register',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './payment-register.component.html',
  styleUrl: './payment-register.component.css'
})
export class PaymentRegisterComponent {

  @Input() payment?: PaymentResponse;
  @Output() close = new EventEmitter<boolean>();

  private fb = inject(NonNullableFormBuilder);
  private paymentService = inject(PaymentService);

  form = this.fb.group({
    paymentAmount: [0, Validators.required],
    observation: ['']
  });

  ngOnInit() {
    if (this.payment) {
      this.form.patchValue({
        ...this.payment,
      });
    }
  }

  save(): void {
    if (!this.payment) {
      return;
    }

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const request = this.form.getRawValue();

    this.paymentService.pay(this.payment.id, request)
      .subscribe(() =>
        this.close.emit(true)
      );
  }


  ngOnChanges(): void {
    if (!this.payment) {
      this.form.reset({
        paymentAmount: 0,
        observation: ''
      });
      return;
    }

    this.form.patchValue({
      paymentAmount: this.payment.amount,
      observation: ''
    });
  }

  cancel() {
    this.close.emit(false);
  }
}