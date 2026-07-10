import { Component, inject } from '@angular/core';
import { JsonPipe } from '@angular/common';
import { StudentService } from '../../../core/service/student.service';


@Component({
  selector: 'app-dashboard',
  imports: [JsonPipe],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  private studentService = inject(StudentService);

  students: any[] = [];

  ngOnInit() {
    this.studentService.getAll().subscribe({
      next: (data) => {
        this.students = data;
        // console.log('Students:', data);
      }
    });
  }
}