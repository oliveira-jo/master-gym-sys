import { Injectable } from '@angular/core';

export interface ToastMessage {
  message: string;
  type: 'success' | 'danger' | 'warning' | 'info';
}

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  private readonly toasts: ToastMessage[] = [];

  get messages(): ToastMessage[] {
    return this.toasts;
  }

  show(
    message: string,
    type: ToastMessage['type'] = 'info'
  ): void {


    this.toasts.push({
      message,
      type
    });

    setTimeout(() => {
      this.remove();
    }, 5000);


  }

  success(message: string): void {
    this.show(message, 'success');
  }

  error(message: string): void {
    this.show(message, 'danger');
  }

  warning(message: string): void {
    this.show(message, 'warning');
  }

  info(message: string): void {
    this.show(message, 'info');
  }

  remove(index: number = 0): void {
    this.toasts.splice(index, 1);
  }

}
