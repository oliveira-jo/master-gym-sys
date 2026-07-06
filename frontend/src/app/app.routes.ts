import { Routes } from '@angular/router';

export const routes: Routes = [

  // Login (sem layout)
  {
    path: 'login',
    loadComponent: () =>
      import('./features/auth/login/login.component')
        .then(c => c.LoginComponent)
  },

  // Área autenticada
  {
    path: '',
    loadComponent: () =>
      import('./layout/main-layout/main-layout.component')
        .then(c => c.MainLayoutComponent),

    children: [

      // Dashboard
      {
        path: '',
        loadComponent: () =>
          import('./features/home/dashboard/dashboard.component')
            .then(c => c.DashboardComponent)
      },

      // Segurança
      {
        path: 'usuarios',
        loadComponent: () =>
          import('./features/user/user-list/user-list.component')
            .then(c => c.UserListComponent)
      },

      // Cadastro
      {
        path: 'alunos',
        loadComponent: () =>
          import('./features/student/student-list/student-list.component')
            .then(c => c.StudentListComponent)
      },

      {
        path: 'modalidades',
        loadComponent: () =>
          import('./features/modality/modality-list/modality-list.component')
            .then(c => c.ModalityListComponent)
      },

      {
        path: 'planos',
        loadComponent: () =>
          import('./features/subscription/subscription-list/subscription-list.component')
            .then(c => c.SubscriptionListComponent)
      },

      // Matrículas
      {
        path: 'matriculas',
        loadComponent: () =>
          import('./features/enrollment/enrollment-list/enrollment-list.component')
            .then(c => c.EnrollmentListComponent)
      },

      {
        path: 'matricular',
        loadComponent: () =>
          import('./features/enrollment/enrollment-form/enrollment-form.component')
            .then(c => c.EnrollmentFormComponent)
      },

      {
        path: 'matriculas/:id/editar',
        loadComponent: () =>
          import('./features/enrollment/enrollment-form/enrollment-form.component')
            .then(c => c.EnrollmentFormComponent)
      },

      // Financeiro
      {
        path: 'faturas',
        loadComponent: () =>
          import('./features/finance/invoice/search-invoice/search-invoice.component')
            .then(c => c.SearchInvoiceComponent)
      },

      {
        path: 'pagamentos',
        loadComponent: () =>
          import('./features/finance/payment/generate-payment/generate-payment.component')
            .then(c => c.GeneratePaymentComponent)
      },

      // Relatórios
      {
        path: 'relatorios',
        loadComponent: () =>
          import('./features/report/enrollment/enrollment.component')
            .then(c => c.EnrollmentComponent)
      }

    ]
  },

  {
    path: '**',
    redirectTo: ''
  }

];