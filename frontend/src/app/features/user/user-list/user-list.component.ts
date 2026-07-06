import { Component, inject, OnInit } from '@angular/core';
import { UserService } from '../../../core/service/user.service';
import { UserResponse } from '../../../core/model/response/user-response.model';
import { PageResponse } from '../../../core/model/page/page-response.model';
import { UserFilterRequest } from '../../../core/model/filter/user-filter.model';
import { PageableRequest } from '../../../core/model/page/pageable-request.model';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { UserFormComponent } from '../user-form/user-form.component';

@Component({
  selector: 'app-user-list',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    UserFormComponent
  ],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent implements OnInit {
  private readonly userService = inject(UserService);

  users: UserResponse[] = [];

  pageResponse?: PageResponse<UserResponse>;

  filter: UserFilterRequest = {};

  page = new PageableRequest(0, 10, 'name');

  totalElements = 0;
  selectedUser?: UserResponse;
  modalOpen = false;

  private fb = inject(FormBuilder);
  searchForm = this.fb.group({
    name: [''], phone: [''], email: [''],
    cpf: [''], password: ['']
  });

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {

    const filter: UserFilterRequest = {
      name: this.searchForm.value.name ?? '',
      email: this.searchForm.value.email ?? ''
    };

    this.userService
      .findAll(filter, this.page)
      .subscribe({

        next: page => {
          this.users = page.content;
          this.totalElements = page.totalElements;
        },

        error: err => console.error(err)

      });

  }

  search(): void {

    this.page.page = 0;

    this.loadUsers();

  }

  clear(): void {
    this.searchForm.reset();
    this.page.page = 0;
    this.loadUsers();
  }

  openCreate(): void {
    this.selectedUser = undefined;
    this.modalOpen = true;
  }

  openEdit(user: UserResponse): void {
    this.selectedUser = user;
    this.modalOpen = true;
  }

  delete(id: number): void {

    if (!confirm('Deseja realmente excluir este usuário?')) {
      return;
    }
    this.userService.delete(id).subscribe({
      next: () => {
        this.loadUsers();
      },
      error: err => console.error(err)

    });

  }

  closeModal(refresh: boolean): void {
    this.modalOpen = false;
    if (refresh) {
      this.loadUsers();
    }
  }

}