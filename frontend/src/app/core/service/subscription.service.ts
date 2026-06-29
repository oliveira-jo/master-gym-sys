import { Injectable } from '@angular/core';
import { SubscriptionRequest } from '../model/subscription-request.model';
import { SubscriptionResponse } from '../model/subscription-response.model';
import { BaseService } from './base.service';
import { PageableRequest } from '../model/page/pageable-request.model';
import { PageResponse } from '../model/page/page-response.model';
import { HttpParamsBuilder } from '../builders/http-params.builder';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService extends BaseService<SubscriptionRequest, SubscriptionResponse> {
  protected override endpoint = 'subscriptions';

  constructor() {
    super();
  }

  findAll(
    pageable: PageableRequest
  ) {

    return this.http.get<PageResponse<SubscriptionResponse>>(
      this.url,
      {
        params: HttpParamsBuilder.build({
          ...pageable
        })
      }
    );

  }
}
