import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MonthlyBilling } from '../model/report/monthly-billing.model';
import { StudentsByCity } from '../model/report/students-by-city.model';
import { OutstandingInvoices } from '../model/report/outstanding-invoices.model';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  private readonly apiUrl = `${environment.apiUrl}/reports`;

  constructor(
    private readonly http: HttpClient
  ) { }

  getMonthlyBilling(): Observable<MonthlyBilling[]> {
    return this.http.get<MonthlyBilling[]>(
      `${this.apiUrl}/monthlyBilling`
    );
  }

  getStudentsByCity(): Observable<StudentsByCity[]> {
    return this.http.get<StudentsByCity[]>(
      `${this.apiUrl}/studentsByCity`
    );
  }

  getOutstandingInvoices(): Observable<OutstandingInvoices[]> {
    return this.http.get<OutstandingInvoices[]>(
      `${this.apiUrl}/outstandingInvoices`
    );
  }

}