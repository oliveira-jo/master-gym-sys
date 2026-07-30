import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { StudentResponse } from '../../../core/model/response/student-response.model';
import { StudentService } from '../../../core/service/student.service';
import { StudentRequest } from '../../../core/model/request/student-request.model';
import { DateUtils } from '../../../core/utils/DateUtils';
import { DocumentUtils } from '../../../core/utils/DocumentUtils';
import { PhoneUtils } from '../../../core/utils/PhoneUtils';
import { STATES } from '../../../core/constant/states';
import { ToastService } from '../../../core/service/toast.service';

@Component({
  selector: 'app-student-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './student-form.component.html'
})
export class StudentFormComponent {

  @Input() student?: StudentResponse;
  @Output() close = new EventEmitter<boolean>();

  private fb = inject(NonNullableFormBuilder);
  private studentService = inject(StudentService);
  private toastService = inject(ToastService);

  states = STATES;

  form = this.fb.group({
    // Princiapl Data
    name: ['', Validators.required],
    birthdate: ['', Validators.required],
    genre: ['', Validators.required],
    cpf: ['', Validators.required],
    phone: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    // Adress
    zipCode: ['', Validators.required],
    state: ['', Validators.required],
    city: ['', Validators.required],
    address: ['', Validators.required],
    number: ['', Validators.required],
    complement: [''],
    // Others
    observations: ['']
  });

  errorMessage = '';

  ngOnInit() {
    if (this.student) {
      this.form.patchValue({
        ...this.student,
        birthdate: DateUtils.toBrazilian(this.student.birthdate)
      });
    }
  }

  save(): void {

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.errorMessage = '';

    const request: StudentRequest = this.form.getRawValue();

    //convert data for ISO
    request.birthdate = DateUtils.toIso(request.birthdate);

    // Format document
    request.cpf = DocumentUtils.onlyNumbers(request.cpf);

    // Format phone
    request.phone = PhoneUtils.format(request.phone);

    // TEST
    // console.table(request);

    if (this.student) {

      this.studentService.update(this.student.id, request)
        .subscribe({
          next: () => {
            this.close.emit(true)
          },
          error: (error) => {
            // console.error('Erro ao editar:', error);
            // this.errorMessage = error.error?.message;
            this.toastService.error('Não foi possível editar o aluno.');

          }
        });

    } else {

      this.studentService.create(request).subscribe({
        next: () => {
          this.close.emit(true)
        },
        error: (error) => {
          // console.error('Erro ao salvar:', error);
          // this.errorMessage = error.error?.message;
          this.toastService.error('Não foi possível salvar o aluno.');
        }
      });
    }
  }

  ngOnChanges(): void {

    if (!this.student) {
      this.form.reset();
      return;
    }

    this.form.patchValue({
      name: this.student.name,
      birthdate: this.student.birthdate,
      genre: this.student.genre,
      cpf: this.student.cpf,
      phone: this.student.phone,
      email: this.student.email,
      zipCode: this.student.zipCode,
      state: this.student.state,
      city: this.student.city,
      address: this.student.address,
      number: this.student.number,
      complement: this.student.complement,
      observations: this.student.observations
    });
  }

  cancel() {
    this.close.emit(false);
  }
}