import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Dashboard, EnrollmentsByStatus, ExpiringEnrollment, MonthlyBilling, NewStudentsByMonth, OutstandingInvoices, PaymentsByStatus, StudentsByCity, StudentsByModality } from '../model/response/dashboard.response.model';

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

  getStudentsByModality(): Observable<StudentsByModality[]> {
    return this.http.get<StudentsByModality[]>(`${this.apiUrl}/students-by-modality`);
  }

  getEnrollmentsByStatus(): Observable<EnrollmentsByStatus[]> {
    return this.http.get<EnrollmentsByStatus[]>(`${this.apiUrl}/enrollments-by-status`);
  }

  getPaymentsByStatus(): Observable<PaymentsByStatus[]> {
    return this.http.get<PaymentsByStatus[]>(`${this.apiUrl}/payments-by-status`);
  }

  getNewStudentsByMonth(): Observable<NewStudentsByMonth[]> {
    return this.http.get<NewStudentsByMonth[]>(`${this.apiUrl}/new-students-by-month`);
  }

  getExpiringEnrollments(): Observable<ExpiringEnrollment[]> {
    return this.http.get<ExpiringEnrollment[]>(`${this.apiUrl}/expiring-enrollments`);
  }

}