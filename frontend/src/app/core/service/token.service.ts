import { Injectable } from "@angular/core";
import { JwtPayload } from "../../shared/interfaces/JwtPayload";
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private readonly TOKEN = 'access_token';

  save(token: string) {
    localStorage.setItem(this.TOKEN, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN);
  }

  remove() {
    localStorage.removeItem(this.TOKEN);
  }

  isAuthenticated() {
    return !!this.getToken();
  }

  getPayload(): JwtPayload | null {
    const token = this.getToken();

    if (!token) {
      return null;
    }

    return jwtDecode<JwtPayload>(token);
  }

  isExpired(): boolean {
    const payload = this.getPayload();

    if (!payload) {
      return true;
    }

    return payload.exp * 1000 < Date.now();
  }

  getAuthorities(): string[] {
    return this.getPayload()?.authorities ?? [];
  }

  getUsername(): string | null {
    return this.getPayload()?.username ?? null;
  }

}
