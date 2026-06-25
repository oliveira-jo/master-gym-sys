import { Injectable } from '@angular/core';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root'
})
export class PaymentService extends BaseService<PaymentRequest, PaymentResponse> {
  protected override endpoint = 'payments';

  constructor() {
    super();
  }
}
