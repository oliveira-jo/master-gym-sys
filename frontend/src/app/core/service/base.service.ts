import { HttpClient } from '@angular/common/http';
import { inject } from '@angular/core';
import { environment } from '../../../environments/environment';

export abstract class BaseService<Request, Response> {

  protected http = inject(HttpClient);

  protected abstract endpoint: string;

  protected get url(): string {
    return `${environment.apiUrl}/${this.endpoint}`;
  }

  getAll() {
    return this.http.get<Response[]>(this.url);
  }

  getById(id: number | string) {
    return this.http.get<Response>(`${this.url}/${id}`);
  }

  create(data: Request) {
    return this.http.post<Response>(this.url, data);
  }

  update(id: number | string, data: Request) {
    return this.http.put<Response>(`${this.url}/${id}`, data);
  }

  delete(id: number | string) {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}