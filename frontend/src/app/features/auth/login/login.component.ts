import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

import { AuthService } from '../../../core/service/auth.service';
import { TokenService } from '../../../core/service/token.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  private fb = inject(NonNullableFormBuilder);
  private authService = inject(AuthService);
  private tokenService = inject(TokenService);
  private router = inject(Router);

  loading = false;
  errorMessage = '';

  form = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });

  login(): void {

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    const { username, password } = this.form.getRawValue();

    this.authService.login(username, password)
      .subscribe({
        next: (response) => {
          this.tokenService.save(response.access_token);
          this.router.navigate(['/dashboard']);
        },
        error: () => {
          this.loading = false;
          this.errorMessage = 'Usuário ou senha inválidos.';
        },
        complete: () => {
          this.loading = false;
        }
      });
  }

  logout() {

    this.tokenService.remove();

    this.router.navigate(['/login']);

  }

}