import { Dashboard, EnrollmentsByStatus, ExpiringEnrollment, NewStudentsByMonth, OutstandingInvoices, PaymentsByStatus, StudentsByModality } from '../../../core/model/response/dashboard.response.model';
import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReportService } from '../../../core/service/report.service';
import { BaseChartDirective } from 'ng2-charts';
import { ChartData, ChartOptions, ChartConfiguration } from 'chart.js';
import { RouterLink } from '@angular/router';


@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    BaseChartDirective,
    RouterLink
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  private readonly reportService = inject(ReportService);

  dashboard?: Dashboard;

  outstandingInvoices: OutstandingInvoices[] = [];
  studentsByModality: StudentsByModality[] = [];
  enrollmentsByStatus: EnrollmentsByStatus[] = [];
  paymentsByStatus: PaymentsByStatus[] = [];
  newStudentsByMonth: NewStudentsByMonth[] = [];
  expiringEnrollments: ExpiringEnrollment[] = [];

  loading = true;

  error?: string;

  ngOnInit(): void {
    this.loadDashboard();
    this.loadOutstandingInvoices();
    this.loadStudentsByModality();
    this.loadEnrollmentsByStatus();
    this.loadPaymentsByStatus();
    this.loadNewStudentsByMonth();
    this.loadExpiringEnrollments();
  }

  loadDashboard(): void {
    this.loading = true;
    this.reportService
      .getDashboard().subscribe({
        next: (response) => {
          this.dashboard = response;
          this.buildBillingChart();
          this.loading = false;
        },
        error: (error) => {
          console.error('Erro ao carregar dashboard:', error);
          this.error = 'Não foi possível carregar o dashboard.';
          this.loading = false;
        }
      });
  }

  loadOutstandingInvoices(): void {
    this.reportService
      .getOutstandingInvoices()
      .subscribe({
        next: (response) => {
          this.outstandingInvoices = response.slice(0, 5);
        },
        error: (error) => {
          console.error('Erro ao carregar pagamentos:', error);
        }
      });
  }

  loadStudentsByModality(): void {
    this.reportService.getStudentsByModality()
      .subscribe({
        next: (response) => {
          this.studentsByModality = response;
          this.buildStudentsByModalityChart();
        },
        error: (error) => {
          console.error('Erro ao carregar alunos por modalidade:', error);
        }
      });
  }

  loadEnrollmentsByStatus(): void {
    this.reportService
      .getEnrollmentsByStatus()
      .subscribe({
        next: (response) => {
          this.enrollmentsByStatus = response;
          this.buildEnrollmentsStatusChart();
        },
        error: (error) => {
          console.error('Erro ao carregar matrículas por status:', error);
        }
      });
  }

  loadPaymentsByStatus(): void {
    this.reportService.getPaymentsByStatus()
      .subscribe({
        next: (response) => {
          this.paymentsByStatus = response;
          this.buildPaymentsStatusChart();
        },
        error: (error) => {
          console.error('Erro ao carregar pagamentos por status:', error);
        }
      });
  }

  loadNewStudentsByMonth(): void {
    this.reportService.getNewStudentsByMonth()
      .subscribe({
        next: (response) => {
          this.newStudentsByMonth = response;
          this.buildNewStudentsChart();
        },
        error: (error) => {
          console.error('Erro ao carregar novos alunos:', error);
        }
      });
  }

  loadExpiringEnrollments(): void {
    this.reportService
      .getExpiringEnrollments()
      .subscribe({
        next: (response) => {
          this.expiringEnrollments = response;
        },
        error: (error) => {
          console.error('Erro ao carregar matrículas próximas do encerramento:', error);
        }
      });
  }

  private buildBillingChart(): void {
    if (!this.dashboard) {
      return;
    }
    const history = this.dashboard.monthlyBillingHistory;
    this.billingChartData = {
      labels:
        history.map(item => item.month),
      datasets: [
        {
          data:
            history.map(item => item.totalBilling),
          label: 'Faturamento mensal',
          fill: true,
          tension: 0.4
        }
      ]
    };
  }

  private buildStudentsByModalityChart(): void {
    this.studentsByModalityChartData = {
      labels:
        this.studentsByModality.map(item => item.modalityName),
      datasets: [
        {
          data:
            this.studentsByModality.map(item => item.quantity)
        }
      ]
    };
  }

  private buildEnrollmentsStatusChart(): void {
    this.enrollmentsStatusChartData = {
      labels:
        this.enrollmentsByStatus.map(
          item => this.formatStatus(item.status)
        ),
      datasets: [
        {
          data: this.enrollmentsByStatus.map(item => item.quantity)
        }
      ]
    };
  }

  private buildPaymentsStatusChart(): void {
    this.paymentsStatusChartData = {
      labels:

        this.paymentsByStatus.map(
          item => this.formatStatus(item.status)
        ),
      datasets: [
        {
          data:
            this.paymentsByStatus.map(item => item.quantity),
          label: 'Quantidade de pagamentos'
        }
      ]
    };
  }

  private buildNewStudentsChart(): void {
    this.newStudentsChartData = {
      labels:
        this.newStudentsByMonth.map(item => this.formatMonth(item.month)),
      datasets: [
        {
          data:
            this.newStudentsByMonth.map(item => item.quantity),
          label: 'Novos alunos'
        }
      ]
    };
  }

  private formatStatus(status: string): string {
    const labels:
      Record<string, string> = {
      ACTIVE: 'Ativa',
      CLOSED: 'Encerrada',
      CANCELED: 'Cancelada',
      PAID: 'Pago',
      OPEN: 'Em aberto',
      OVERDUE: 'Vencido'
    };
    return (labels[status] ?? status);
  }

  private formatMonth(value: string): string {
    const [year, month] = value.split('-');
    const date = new Date(Number(year), Number(month) - 1, 1);
    return new Intl.DateTimeFormat('pt-BR', { month: 'short', year: 'numeric' }).format(date).replace('.', '');
  }

  //Graphic datas
  public studentsByModalityChartData: ChartData<'doughnut'> = {
    labels: [],
    datasets: [
      {
        data: []
      }
    ]
  };

  public enrollmentsStatusChartData: ChartData<'doughnut'> = {
    labels: [],
    datasets: [
      {
        data: []
      }
    ]
  };

  public paymentsStatusChartData: ChartData<'bar'> = {
    labels: [],
    datasets: [
      {
        data: [],
        label: 'Quantidade'
      }
    ]
  };

  public newStudentsChartData: ChartData<'bar'> = {
    labels: [],
    datasets: [
      {
        data: [],
        label: 'Novos alunos'
      }
    ]
  };

  public billingChartData: ChartData<'line'> = {
    labels: [],
    datasets: [
      {
        data: [],
        label: 'Faturamento',
        fill: true,
        tension: 0.4
      }
    ]
  };

  public billingChartOptions: ChartOptions<'line'> = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: true
      }
    },
    scales: {
      y: {
        beginAtZero: true
      }
    }
  };

  public doughnutChartOptions: ChartOptions<'doughnut'> = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        position: 'bottom'
      }
    }
  };

  public barChartOptions: ChartOptions<'bar'> = {
    responsive: true,
    maintainAspectRatio: false,
    scales: {
      y: {
        beginAtZero: true,
        ticks: {
          precision: 0
        }
      }
    },
    plugins: {
      legend: {
        display: false
      }
    }
  };

}