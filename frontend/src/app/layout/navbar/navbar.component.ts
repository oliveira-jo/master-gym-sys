import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MENU_CONFIG } from '../../core/config/menu.config';
import { MenuItem } from '../../core/model/menu-item';
import { AuthService } from '../../core/service/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './navbar.component.html'
})
export class NavbarComponent {

  private authService = inject(AuthService);

  menus: MenuItem[] = MENU_CONFIG;

  logout(): void {
    this.authService.logout();
  }

}