import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { StudentResponse } from '../../../core/model/student-response.model';
import { StudentService } from '../../../core/service/student.service';
import { StudentRequest } from '../../../core/model/student-request.model';

@Component({
  selector: 'app-student-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './student-form.component.html'
})
export class StudentFormComponent {

  @Input() student?: StudentResponse;
  @Output() close = new EventEmitter<boolean>();

  private fb = inject(FormBuilder);
  private studentService = inject(StudentService);

  form = this.fb.group({
    name: ['', Validators.required],
    email: ['', Validators.required],
    phone: ['', Validators.required],
    cpf: ['', Validators.required],
    genre: ['', Validators.required],
    birthdate: ['', Validators.required],
    address: ['', Validators.required],
    number: ['', Validators.required],
    city: ['', Validators.required],
    state: ['', Validators.required],
    zipCode: ['', Validators.required]
  });

  ngOnInit() {
    if (this.student) {
      this.form.patchValue(this.student);
    }
  }

  save() {

    if (this.form.invalid) return;

    const payload = this.form.value as StudentRequest;

    if (this.student) {

      this.studentService.update(this.student.id, payload)
        .subscribe(() => this.close.emit(true));

    } else {

      this.studentService.create(payload)
        .subscribe(() => this.close.emit(true));

    }
  }

  cancel() {
    this.close.emit(false);
  }
}