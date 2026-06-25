import { Injectable } from '@angular/core';
import { EnrollmentResponse } from '../model/Enrollment-response.model';
import { EnrollmentRequest } from '../model/enrollment-request.model';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root'
})
export class EnrollmentService extends BaseService<EnrollmentRequest, EnrollmentResponse> {
  protected override endpoint = 'enrollments';

  constructor() {
    super();
  }
}
