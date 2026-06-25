import { Injectable } from '@angular/core';
import { SubscriptionRequest } from '../model/subscription-request.model';
import { SubscriptionResponse } from '../model/subscription-response.model';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService extends BaseService<SubscriptionRequest, SubscriptionResponse> {

  protected override endpoint = 'subscriptions';

  constructor() {
    super();
  }
}
