import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private readonly TOKEN = 'access_token';

  save(token: string) {
    localStorage.setItem(this.TOKEN, token);
  }

  getToken() {
    return localStorage.getItem(this.TOKEN);
  }

  remove() {
    localStorage.removeItem(this.TOKEN);
  }

  isAuthenticated() {
    return !!this.getToken();
  }

}