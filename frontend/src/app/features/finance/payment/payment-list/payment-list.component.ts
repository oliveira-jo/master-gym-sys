import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormsModule, Validators } from '@angular/forms';
import { EnrollmentService } from '../../../../core/service/enrollment.service';
import { PaymentService } from '../../../../core/service/payment.service';
import { EnrollmentResponse } from '../../../../core/model/response/enrollment-response.model';
import { PaymentHistoryResponse } from '../../../../core/model/response/payment-history-response.model';
import { PageableRequest } from '../../../../core/model/page/pageable-request.model';
import { PageResponse } from '../../../../core/model/page/page-response.model';
import { PaymentResponse } from '../../../../core/model/response/payment-response-model';
import { PaymentRegisterComponent } from "../payment-register/payment-register.component";


@Component({
  selector: 'app-payment-list',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    PaymentRegisterComponent
  ],
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.css']
})
export class PaymentListComponent implements OnInit {

  enrollments: EnrollmentResponse[] = [];

  selectedEnrollmentId?: number;

  selectedPaymentId?: number;

  history?: PaymentHistoryResponse;

  page = new PageableRequest(0, 10, 'id');

  pageResponse?: PageResponse<PaymentResponse>;

  totalElements = 0;

  private fb = inject(FormBuilder);

  selectedPayment?: PaymentResponse;
  modalOpen = false;

  constructor(
    private enrollmentService: EnrollmentService,
    private paymentService: PaymentService
  ) {
  }

  ngOnInit(): void {
    this.loadEnrollments();
  }

  loadEnrollments() {

    this.enrollmentService.findAll(this.page)
      .subscribe({
        next: response => {
          this.enrollments = response.content;
        }
      });
  }

  onEnrollmentChange() {
    if (!this.selectedEnrollmentId) {
      this.history = undefined;
      return;
    }
    this.paymentService
      .findHistory(this.selectedEnrollmentId)
      .subscribe({
        next: response => {
          this.history = response;
          this.totalElements = response.payments.totalElements;
        }
      });
  }

  // pay(id: number): void {
  //   console.log('paymenti id ', id);

  //   this.selectedPaymentId = id;

  //   this.paymentService.pay(this.selectedPaymentId)
  //     .subscribe({
  //       next: () => {
  //         this.onEnrollmentChange();
  //       },
  //       error: err => {
  //         console.error(err);
  //       }
  //     });
  // }

  // cancel(id: number): void {
  //   if (!this.selectedPaymentId) {
  //     this.history = undefined;
  //     return;
  //   }

  //   this.paymentService.cancel(this.selectedPaymentId)
  //     .subscribe({
  //       next: () => {
  //         this.onEnrollmentChange();
  //       },
  //       error: err => {
  //         console.log(err);
  //       }
  //     });
  // }

  //MODAL
  openCreate(): void {
    this.selectedPayment = undefined;
    this.modalOpen = true;
  }

  openPay(payment: PaymentResponse): void {
    this.selectedPayment = payment
    this.modalOpen = true;
  }

  delete(id: number): void {

    if (!confirm('Deseja realmente excluir este pagamento?')) {
      return;
    }

    this.paymentService.delete(id).subscribe({
      next: () => {
        this.onEnrollmentChange();
      },
      error: err => console.error(err)

    });
  }

  closeModal(refresh: boolean): void {
    this.modalOpen = false;
    if (refresh) {
      this.onEnrollmentChange();
    }
  }

  //PAGINATION
  nextPage(): void {
    if (!this.pageResponse?.last) {
      this.page.page++;
      this.loadEnrollments();
    }
  }

  previousPage(): void {
    if (!this.pageResponse?.first) {
      this.page.page--;
      this.loadEnrollments();
    }
  }

  goToPage(page: number): void {
    this.page.page = page;
    this.loadEnrollments();
  }

  //PAGE NUMBERS
  get pages(): number[] {
    if (!this.pageResponse) {
      return [];
    }
    return Array.from(
      { length: this.pageResponse.totalPages }, // array length
      (_, i) => i // value, i retorn i
    );
  }

}