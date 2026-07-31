import { MonthlyBilling, OutstandingInvoices, StudentsByCity } from "../../core/model/response/dashboard.response.model";
import { CommonModule, CurrencyPipe, DatePipe } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { ReportService } from "../../core/service/report.service";
import { ToastService } from "../../core/service/toast.service";
import { forkJoin } from "rxjs";

@Component({
  selector: 'app-report',
  standalone: true,
  imports: [
    CommonModule,
    CurrencyPipe,
    DatePipe
  ],
  templateUrl: './report.component.html',
  styleUrl: './report.component.css'
})
export class ReportComponent implements OnInit {

  monthlyBilling: MonthlyBilling[] = [];
  studentsByCity: StudentsByCity[] = [];
  outstandingInvoices: OutstandingInvoices[] = [];

  loading = false;

  errorMessage = '';

  constructor(
    private readonly reportService: ReportService,
    private toastService: ToastService) { }

  ngOnInit(): void {
    this.loadReports();
  }

  loadReports(): void {

    this.loading = true;

    this.errorMessage = '';

    forkJoin({
      monthlyBilling: this.reportService.getMonthlyBilling(),
      studentsByCity: this.reportService.getStudentsByCity(),
      outstandingInvoices: this.reportService.getOutstandingInvoices()
    }).subscribe({
      next: (response) => {

        this.monthlyBilling = response.monthlyBilling;
        this.studentsByCity = response.studentsByCity;
        this.outstandingInvoices = response.outstandingInvoices;
        this.loading = false;
      },
      error: (error) => {
        // console.error('Erro ao carregar relatórios:', error);
        // this.errorMessage = 'Não foi possível carregar os relatórios.';
        this.toastService.error('Não foi possível carregar os relatórios.');
        this.loading = false;
      }
    });

  }

}