import { Injectable } from '@angular/core';
import { BaseService } from './base.service';
import { StudentRequest } from '../model/student-request.model';
import { StudentResponse } from '../model/student-response.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService extends BaseService<StudentRequest, StudentResponse> {

  protected override endpoint = 'students';

  constructor() {
    super();
  }

}