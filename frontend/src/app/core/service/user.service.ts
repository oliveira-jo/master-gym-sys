import { Injectable } from '@angular/core';
import { UserRequest } from '../model/user-request.model';
import { UserResponse } from '../model/user-response.model';
import { BaseService } from './base.service';
import { PageableRequest } from '../model/page/pageable-request.model';
import { PageResponse } from '../model/page/page-response.model';
import { HttpParamsBuilder } from '../builders/http-params.builder';
import { UserFilterRequest } from '../model/user-filter.model';

@Injectable({
  providedIn: 'root'
})
export class UserService extends BaseService<UserRequest, UserResponse> {
  protected override endpoint = 'users';

  constructor() {
    super();
  }

  findAll(
    filter: UserFilterRequest,
    pageable: PageableRequest
  ) {

    return this.http.get<PageResponse<UserResponse>>(
      this.url,
      {
        params: HttpParamsBuilder.build({
          ...filter,
          ...pageable
        })
      }
    );

  }

}
