import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { TokenResponse } from '../model/response/token-response';
import { TokenService } from './token.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly API = environment.apiUrl + '/oauth2/token';

  constructor(
    private http: HttpClient,
    private tokenService: TokenService,
    private router: Router) { }

  login(username: string, password: string) {
    const body = new HttpParams()
      .set('username', username)
      .set('password', password)
      .set('grant_type', 'password');

    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa('myclientid:myclientsecret'),
      'Content-Type': 'application/x-www-form-urlencoded'
    });

    return this.http.post<TokenResponse>(
      this.API,
      body.toString(),
      { headers }
    );
  }

  logout(): void {
    this.tokenService.remove();
    this.router.navigate(['/login']);
  }

  hasRole(role: string): boolean {
    return this.tokenService.getAuthorities().includes(role);
  }

  hasAnyRole(roles: string[]): boolean {
    return roles.some(role => this.hasRole(role));
  }

}