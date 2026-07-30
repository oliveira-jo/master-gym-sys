import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReportService } from '../../../core/service/report.service';
import { Dashboard } from '../../../core/model/response/dashboard.response.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule
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
          this.loading = false;
        },
        error: (error) => {
          console.error('Erro ao carregar dashboard:', error);
          this.error = 'Não foi possível carregar o dashboard.';
          this.loading = false;
        }
      });

  }
}