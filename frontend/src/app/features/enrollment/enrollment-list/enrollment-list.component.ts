import { Component, inject, OnInit } from '@angular/core';
import { EnrollmentService } from '../../../core/service/enrollment.service';
import { EnrollmentResponse } from '../../../core/model/response/enrollment-response.model';
import { PageResponse } from '../../../core/model/page/page-response.model';
import { PageableRequest } from '../../../core/model/page/pageable-request.model';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { EnrollmentFormComponent } from "../enrollment-form/enrollment-form.component";
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from "@angular/router";

@Component({
  selector: 'app-enrollment-list',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    EnrollmentFormComponent,
    RouterLink
  ],
  templateUrl: './enrollment-list.component.html',
})
export class EnrollmentListComponent implements OnInit {

  private readonly enrollmentService = inject(EnrollmentService);

  private router = inject(Router);

  enrollments: EnrollmentResponse[] = [];

  pageResponse?: PageResponse<EnrollmentResponse>;

  page = new PageableRequest(0, 10, 'id');

  totalElements = 0;
  selectedEnrollment?: EnrollmentResponse;
  modalOpen = false;

  // 
  private fb = inject(FormBuilder);
  searchForm = this.fb.group({
    enrollmentDate: [''],
    dueDay: [''],
    closingDate: [''],
    studentId: [''],
  });


  ngOnInit(): void {
    this.loadEnrollments();
  }

  loadEnrollments(): void {

    this.enrollmentService
      .findAll(this.page)
      .subscribe({

        next: page => {

          //pagination
          this.pageResponse = page;

          this.enrollments = page.content;
          this.totalElements = page.totalElements;
        },

        error: err => console.error(err)

      });

  }

  search(): void {
    this.page.page = 0;
    this.loadEnrollments();
  }

  clear(): void {
    this.searchForm.reset();
    this.page.page = 0;
    this.loadEnrollments();
  }

  openCreate(): void {
    this.selectedEnrollment = undefined;
    this.modalOpen = true;
  }

  openEdit(enrollment: EnrollmentResponse): void {
    this.selectedEnrollment = enrollment;
    this.router.navigate(['/matriculas', enrollment.id, 'editar']);
  }

  delete(id: number): void {

    if (!confirm('Deseja realmente excluir este inscrição?')) {
      return;
    }
    this.enrollmentService.delete(id).subscribe({
      next: () => {
        this.loadEnrollments();
      },
      error: err => console.error(err)

    });

  }

  closeModal(refresh: boolean): void {
    this.modalOpen = false;
    if (refresh) {
      this.loadEnrollments();
    }
  }

  //pagination
  nextPage(): void {
    if (!this.pageResponse?.last) {
      this.page.page++;
      this.loadEnrollments();
    }
  }

  previousPage(): void {
    if (!this.pageResponse?.first) {
      this.page.page--;
      this.loadEnrollments();
    }
  }

  goToPage(page: number): void {
    this.page.page = page;
    this.loadEnrollments();
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

