import { Injectable } from '@angular/core';
import { StudentFilterRequest } from '../model/filter/student-filter.model';
import { StudentRequest } from '../model/request/student-request.model';
import { StudentResponse } from '../model/response/student-response.model';
import { BaseService } from './base.service';
import { PageResponse } from '../model/page/page-response.model';
import { HttpParamsBuilder } from '../builders/http-params.builder';
import { PageableRequest } from '../model/page/pageable-request.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService extends BaseService<StudentRequest, StudentResponse> {
  protected override endpoint = 'students';

  findAll(
    filter: StudentFilterRequest,
    pageable: PageableRequest
  ) {

    return this.http.get<PageResponse<StudentResponse>>(
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