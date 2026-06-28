import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentFormComponent } from '../student-form/student-form.component';
import { StudentService } from '../../../core/service/student.service';
import { StudentResponse } from '../../../core/model/student-response.model';
import { StudentFilterRequest } from '../../../core/model/student-filter.model';
import { PageableRequest } from '../../../core/model/page/pageable-request.model';
import { PageResponse } from '../../../core/model/page/page-response.model';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    StudentFormComponent
  ],
  templateUrl: './student-list.component.html'
})
export class StudentListComponent implements OnInit {

  private readonly studentService = inject(StudentService);

  students: StudentResponse[] = [];

  pageResponse?: PageResponse<StudentResponse>;

  filter: StudentFilterRequest = {};

  page = new PageableRequest(0, 10, 'name');

  totalElements = 0;
  selectedStudent?: StudentResponse;
  modalOpen = false;

  // 
  private fb = inject(FormBuilder);
  searchForm = this.fb.group({
    name: [''], birthdate: [''], genre: [''], phone: [''], email: [''],
    cpf: [''], observations: [''], address: [''], number: [''], complement: [''],
    city: [''], state: [''], zipCode: [''],
  });

  ngOnInit(): void {
    this.loadStudents();
  }

  loadStudents(): void {

    const filter: StudentFilterRequest = {
      name: this.searchForm.value.name ?? '',
      email: this.searchForm.value.email ?? ''
    };

    this.studentService
      .findAll(filter, this.page)
      .subscribe({

        next: page => {
          this.students = page.content;
          this.totalElements = page.totalElements;
        },

        error: err => console.error(err)

      });

  }

  search(): void {

    this.page.page = 0;

    this.loadStudents();

  }

  clear(): void {
    this.searchForm.reset();
    this.page.page = 0;
    this.loadStudents();
  }

  openCreate(): void {
    this.selectedStudent = undefined;
    this.modalOpen = true;
  }

  openEdit(student: StudentResponse): void {
    this.selectedStudent = student;
    this.modalOpen = true;
  }

  delete(id: number): void {

    if (!confirm('Deseja realmente excluir este aluno?')) {
      return;
    }
    this.studentService.delete(id).subscribe({
      next: () => {
        this.loadStudents();
      },
      error: err => console.error(err)

    });

  }

  closeModal(refresh: boolean): void {
    this.modalOpen = false;
    if (refresh) {
      this.loadStudents();
    }
  }

}