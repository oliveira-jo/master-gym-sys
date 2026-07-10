import { HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { TokenService } from "../service/token.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private tokenService: TokenService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler) {

    const token = this.tokenService.getToken();

    if (!token) {
      return next.handle(req);
    }

    const request = req.clone({

      setHeaders: {
        Authorization: `Bearer ${token}`
      }

    });

    return next.handle(request);

  }

}