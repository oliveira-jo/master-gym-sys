import { Injectable } from '@angular/core';
import { ModalityRequest } from '../model/modality-request.model';
import { ModalityResponse } from '../model/modality-response.model';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root'
})
export class ModalityService extends BaseService<ModalityRequest, ModalityResponse> {
  protected override endpoint = 'modalities';

  constructor() {
    super();
  }
}
