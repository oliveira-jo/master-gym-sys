import { Injectable } from '@angular/core';
import { BaseService } from './base.service';
import { PageableRequest } from '../model/page/pageable-request.model';
import { PageResponse } from '../model/page/page-response.model';
import { HttpParamsBuilder } from '../builders/http-params.builder';
import { PaymentHistoryResponse } from '../model/response/payment-history-response.model';
import { PaymentMinRequest } from '../model/request/payment-min-request.model';

@Injectable({
  providedIn: 'root'
})
export class PaymentService extends BaseService<PaymentRequest, PaymentResponse> {
  protected override endpoint = 'payments';

  constructor() {
    super();
  }

  findAll(
    pageable: PageableRequest
  ) {

    return this.http.get<PageResponse<PaymentResponse>>(
      this.url,
      {
        params: HttpParamsBuilder.build({
          ...pageable
        })
      }
    );

  }

  findHistory(id: number) {
    return this.http.get<PaymentHistoryResponse>(
      `${this.url}/enrollment/${id}/history`
    );
  }

  pay(id: number, request: PaymentMinRequest) {
    return this.http.put<void>(`${this.url}/${id}/pay`, request);
  }

  cancel(id: number) {
    return this.http.put<void>(`${this.url}/${id}/cancel`, null);
  }

}
