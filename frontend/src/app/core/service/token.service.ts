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

  isAuthenticated(): boolean {
    return !this.isTokenExpired();
  }

  getPayload(): JwtPayload | null {
    const token = this.getToken();

    if (!token) {
      return null;
    }

    try {
      return jwtDecode<JwtPayload>(token);
    } catch {
      return null;
    }

  }

  // isExpired(): boolean {
  //   const payload = this.getPayload();

  //   if (!payload) {
  //     return true;
  //   }

  //   return payload.exp * 1000 < Date.now();
  // }

  isTokenExpired(): boolean {

    const token = this.getToken();

    if (!token) {
      return true;
    }

    try {
      const decoded = jwtDecode<JwtPayload>(token);
      if (!decoded.exp) {
        return true;
      }
      const now = Math.floor(Date.now() / 1000);
      return decoded.exp <= now;

    } catch {
      return true;

    }

  }

  getAuthorities(): string[] {
    return this.getPayload()?.authorities ?? [];
  }

  getUsername(): string | null {
    return this.getPayload()?.username ?? null;
  }

}
