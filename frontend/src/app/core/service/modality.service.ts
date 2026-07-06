import { Injectable } from '@angular/core';
import { ModalityRequest } from '../model/request/modality-request.model';
import { ModalityResponse } from '../model/response/modality-response.model';
import { BaseService } from './base.service';
import { PageableRequest } from '../model/page/pageable-request.model';
import { PageResponse } from '../model/page/page-response.model';
import { HttpParamsBuilder } from '../builders/http-params.builder';

@Injectable({
  providedIn: 'root'
})
export class ModalityService extends BaseService<ModalityRequest, ModalityResponse> {
  protected override endpoint = 'modalities';

  constructor() {
    super();
  }

  findAll(
    pageable: PageableRequest
  ) {

    return this.http.get<PageResponse<ModalityResponse>>(
      this.url,
      {
        params: HttpParamsBuilder.build({
          ...pageable
        })
      }
    );

  }
}
