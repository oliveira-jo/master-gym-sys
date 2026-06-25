import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentFormComponent } from '../student-form/student-form.component';
import { StudentService } from '../../../core/service/student.service';
import { StudentResponse } from '../../../core/model/student-response.model';

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [CommonModule, StudentFormComponent],
  templateUrl: './student-list.component.html'
})
export class StudentListComponent {

  private studentService = inject(StudentService);

  students: StudentResponse[] = [];

  selectedStudent?: StudentResponse;

  modalOpen = false;

  ngOnInit() {
    this.load();
  }

  load() {
    this.studentService.getAll().subscribe({
      next: (data) => this.students = data
    });
  }

  openCreate() {
    this.selectedStudent = undefined;
    this.modalOpen = true;
  }

  openEdit(student: StudentResponse) {
    this.selectedStudent = student;
    this.modalOpen = true;
  }

  delete(id: number) {
    this.studentService.delete(id).subscribe(() => {
      this.load();
    });
  }

  closeModal(refresh: boolean) {
    this.modalOpen = false;
    if (refresh) this.load();
  }
}