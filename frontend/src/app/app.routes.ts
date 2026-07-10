import { Routes } from '@angular/router';
import { authGuard } from './core/guard/auth.guard';

export const routes: Routes = [

  // Público
  {
    path: 'login',
    loadComponent: () =>
      import('./features/auth/login/login.component')
        .then(c => c.LoginComponent)
  },

  // Privado
  {
    path: '',
    loadComponent: () =>
      import('./layout/main-layout/main-layout.component')
        .then(c => c.MainLayoutComponent),

    canActivateChild: [authGuard],

    children: [

      {
        path: '',
        loadComponent: () =>
          import('./features/home/dashboard/dashboard.component')
            .then(c => c.DashboardComponent)
      },

      {
        path: 'usuarios',
        loadComponent: () =>
          import('./features/user/user-list/user-list.component')
            .then(c => c.UserListComponent)
      },

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

      {
        path: 'matriculas',
        loadComponent: () =>
          import('./features/enrollment/enrollment-list/enrollment-list.component')
            .then(c => c.EnrollmentListComponent)
      },

      {
        path: 'matriculas/matricular',
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

      // resto das rotas...
    ]
  },

  {
    path: '**',
    redirectTo: ''
  }

];