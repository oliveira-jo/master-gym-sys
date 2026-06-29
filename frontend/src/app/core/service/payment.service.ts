import { Injectable } from '@angular/core';
import { BaseService } from './base.service';
import { PageableRequest } from '../model/page/pageable-request.model';
import { PageResponse } from '../model/page/page-response.model';
import { HttpParamsBuilder } from '../builders/http-params.builder';

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
}
