import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MenuItem } from '../../core/models/menu-item';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  menus: MenuItem[] = [

    {
      label: 'Sistemas',
      children: [
        {
          label: 'Usuários',
          route: '/usuarios'
        },
        {
          label: 'Sair'
        }
      ]
    },

    {
      label: 'Cadastros',
      children: [
        {
          label: 'Alunos',
          route: '/alunos'
        },
        {
          label: 'Modalidades',
          route: '/modalidades'
        },
        {
          label: 'Planos',
          route: '/planos'
        }
      ]
    },

    {
      label: 'Processos',
      children: [
        {
          label: 'Matricular',
          route: '/matriculas'
        },
        {
          label: 'Gerar Faturas',
          route: '/faturas'
        },
        {
          label: 'Pagamentos',
          route: '/pagamentos'
        }
      ]
    },

    {
      label: 'Relatórios',
      children: [
        {
          label: 'Matrículas',
          route: '/relatorios'
        }
      ]
    }

  ];
}


