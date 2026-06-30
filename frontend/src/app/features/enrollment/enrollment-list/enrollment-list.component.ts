import { Component, inject, OnInit } from '@angular/core';
import { EnrollmentFormComponent } from '../enrollment-form/enrollment-form.component';
import { EnrollmentService } from '../../../core/service/enrollment.service';
import { EnrollmentResponse } from '../../../core/model/enrollment-response.model';
import { PageResponse } from '../../../core/model/page/page-response.model';
import { PageableRequest } from '../../../core/model/page/pageable-request.model';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-enrollment-list',
  imports: [EnrollmentFormComponent],
  templateUrl: './enrollment-list.component.html',
  styleUrl: './enrollment-list.component.css'
})
export class EnrollmentListComponent implements OnInit {

  private readonly enrollmentService = inject(EnrollmentService);

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
    this.modalOpen = true;
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

}
