import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { catchError, Observable, throwError } from "rxjs";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { TokenService } from "../service/token.service";
import { ToastService } from "../service/toast.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private tokenService: TokenService,
    private router: Router,
    private toastService: ToastService
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {

    const token = this.tokenService.getToken();

    const request = token
      ? req.clone({
        setHeaders: {
          Authorization: `Bearer ${token} `
        }
      })
      : req;

    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {

        // Token inválido ou expirado
        if (error.status === 401) {
          this.tokenService.remove();
          this.router.navigate(['/login']);
          this.toastService.warning('Sua sessão expirou. Faça login novamente.');
        }

        // API desligada, servidor inacessível
        if (error.status === 0) {
          this.toastService.error('Não foi possível conectar ao servidor.');
        }

        // Erro interno do backend
        if (error.status >= 500) {
          this.toastService.error('O servidor apresentou um erro. Tente novamente.');
        }

        return throwError(() => error);

      })
    );

  }

}
