import { Injectable } from '@angular/core';
import { EnrollmentRequest } from '../model/enrollment-request.model';
import { BaseService } from './base.service';
import { EnrollmentResponse } from '../model/enrollment-response.model';
import { PageableRequest } from '../model/page/pageable-request.model';
import { PageResponse } from '../model/page/page-response.model';
import { HttpParamsBuilder } from '../builders/http-params.builder';

@Injectable({
  providedIn: 'root'
})
export class EnrollmentService extends BaseService<EnrollmentRequest, EnrollmentResponse> {
  protected override endpoint = 'enrollments';

  constructor() {
    super();
  }

  findAll(
    pageable: PageableRequest
  ) {

    return this.http.get<PageResponse<EnrollmentResponse>>(
      this.url,
      {
        params: HttpParamsBuilder.build({
          ...pageable
        })
      }
    );
  }
}
