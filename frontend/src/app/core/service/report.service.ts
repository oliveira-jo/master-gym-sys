import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MonthlyBilling } from '../model/report/monthly-billing.model';
import { StudentsByCity } from '../model/report/students-by-city.model';
import { OutstandingInvoices } from '../model/report/outstanding-invoices.model';
import { environment } from '../../../environments/environment';
import { Dashboard } from '../model/response/dashboard.response.model';

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
      `${this.apiUrl}/monthly-billing`
    );
  }

  getStudentsByCity(): Observable<StudentsByCity[]> {
    return this.http.get<StudentsByCity[]>(
      `${this.apiUrl}/students-by-city`
    );
  }

  getOutstandingInvoices(): Observable<OutstandingInvoices[]> {
    return this.http.get<OutstandingInvoices[]>(
      `${this.apiUrl}/outstanding-invoices`
    );
  }

  getDashboard(): Observable<Dashboard> {
    return this.http.get<Dashboard>(
      `${this.apiUrl}/dashboard`
    );
  }

}