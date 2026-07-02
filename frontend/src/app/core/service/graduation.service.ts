import { Injectable } from "@angular/core";
import { GraduationResponse } from "../model/graduation-response.model";
import { GraduationRequest } from "../model/graduation-request.model";
import { BaseService } from "./base.service";
import { PageableRequest } from "../model/page/pageable-request.model";
import { PageResponse } from "../model/page/page-response.model";
import { HttpParamsBuilder } from "../builders/http-params.builder";


@Injectable({
  providedIn: 'root'
})
export class GraduationService extends BaseService<GraduationRequest, GraduationResponse> {
  protected override endpoint = 'graduations';

  constructor() {
    super();
  }

  findAll(
    pageable: PageableRequest
  ) {

    return this.http.get<PageResponse<GraduationResponse>>(
      this.url,
      {
        params: HttpParamsBuilder.build({
          ...pageable
        })
      }
    );

  }
}
