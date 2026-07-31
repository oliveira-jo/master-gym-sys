import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReportService } from '../../../core/service/report.service';
import { Dashboard } from '../../../core/model/response/dashboard.response.model';
import { BaseChartDirective } from 'ng2-charts';
import { ChartData, ChartOptions } from 'chart.js';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    BaseChartDirective
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  private readonly reportService = inject(ReportService);

  dashboard?: Dashboard;

  loading = true;

  error?: string;

  ngOnInit(): void {
    this.loadDashboard();
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

  public billingChartData:
    ChartData<'line'> = {
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

  public billingChartOptions:
    ChartOptions<'line'> = {
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


  private buildBillingChart(): void {
    if (!this.dashboard) {
      return;
    }

    const history = this.dashboard.monthlyBillingHistory;

    this.billingChartData = {
      labels:
        history.map(
          item => item.month
        ),
      datasets: [
        {
          data:
            history.map(
              item => item.total
            ),
          label: 'Faturamento mensal',
          fill: true,
          tension: 0.4
        }
      ]
    };
  }

}