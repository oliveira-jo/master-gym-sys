import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { ToastMessage, ToastService } from '../../../core/service/toast.service';

@Component({
  selector: 'app-toast',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './toast.component.html',
  styleUrl: './toast.component.css'
})
export class ToastComponent {

  readonly toastService = inject(ToastService);

  remove(index: number): void {
    this.toastService.remove(index);
  }

  getIcon(type: ToastMessage['type']): string {


    switch (type) {

      case 'success':
        return 'fa-check-circle';

      case 'danger':
        return 'fa-times-circle';

      case 'warning':
        return 'fa-exclamation-triangle';

      default:
        return 'fa-info-circle';

    }


  }

}
