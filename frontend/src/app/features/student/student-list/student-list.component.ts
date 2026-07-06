import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentFormComponent } from '../student-form/student-form.component';
import { StudentService } from '../../../core/service/student.service';
import { StudentResponse } from '../../../core/model/response/student-response.model';
import { StudentFilterRequest } from '../../../core/model/filter/student-filter.model';
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

  filter: StudentFilterRequest = {};

  pageResponse?: PageResponse<StudentResponse>;

  page = new PageableRequest(0, 10, 'name');

  totalElements = 0;

  selectedStudent?: StudentResponse;

  modalOpen = false;

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

          //pagination
          this.pageResponse = page;

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

  //pagination
  nextPage(): void {
    if (!this.pageResponse?.last) {
      this.page.page++;
      this.loadStudents();
    }
  }

  previousPage(): void {
    if (!this.pageResponse?.first) {
      this.page.page--;
      this.loadStudents();
    }

  }

  goToPage(page: number): void {
    this.page.page = page;
    this.loadStudents();
  }

  //generate page number
  get pages(): number[] {
    if (!this.pageResponse) {
      return [];
    }
    return Array.from(
      { length: this.pageResponse.totalPages }, // array length
      (_, i) => i // value, i retorn i
    );
  }

}