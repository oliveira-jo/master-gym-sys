import { MenuItem } from '../model/menu-item';

export const MENU_CONFIG: MenuItem[] = [

  {
    label: 'Sistemas',
    children: [
      { label: 'Usuários', route: '/usuarios' }
    ]
  },

  {
    label: 'Cadastros',
    children: [
      { label: 'Alunos', route: '/alunos' },
      { label: 'Modalidades', route: '/modalidades' },
      { label: 'Planos', route: '/planos' }
    ]
  },

  {
    label: 'Processos',
    children: [
      { label: 'Matricular', route: '/matriculas' },
      { label: 'Financeiro', route: '/financeiro' },
    ]
  },

  {
    label: 'Relatórios',
    children: [
      { label: 'Matrículas', route: '/relatorios' }
    ]
  }

];