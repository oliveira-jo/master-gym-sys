import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserResponse } from '../../../core/model/response/user-response.model';
import { UserService } from '../../../core/service/user.service';
import { UserRequest } from '../../../core/model/request/user-request.model';
import { DocumentUtils } from '../../../core/utils/DocumentUtils';
import { PhoneUtils } from '../../../core/utils/PhoneUtils';
import { STATES } from '../../../core/constant/states';
import { DateUtils } from '../../../core/utils/DateUtils';
import { passwordMatchValidator } from '../../../core/validators/password-match';
import { ToastService } from '../../../core/service/toast.service';

@Component({
  selector: 'app-user-form',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './user-form.component.html',
  styleUrl: './user-form.component.css'
})
export class UserFormComponent {

  @Input() user?: UserResponse;
  @Output() close = new EventEmitter<boolean>();

  private fb = inject(NonNullableFormBuilder);
  private userService = inject(UserService);
  private toastService = inject(ToastService);

  states = STATES;

  form = this.fb.group({
    // Princiapl Data
    name: ['', Validators.required],
    cpf: ['', Validators.required],
    phone: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: ['',
      [
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(50),
        Validators.pattern(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$/)
      ]
    ],
    confirmPassword: ['', Validators.required],
    birthdate: ['', Validators.required],
    // Adress
    address: ['', Validators.required],
    number: ['', Validators.required],
    state: ['', Validators.required],
    complement: [''],
    city: ['', Validators.required],
    zipCode: ['', Validators.required],
  },
    {
      validators: passwordMatchValidator
    }
  );

  errorMessage = '';

  ngOnInit() {
    if (this.user) {
      this.form.patchValue({
        ...this.user,
        birthdate: DateUtils.toBrazilian(this.user.birthdate)
      });
    }
  }

  save(): void {

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const request: UserRequest = this.form.getRawValue();

    //convert data for ISO
    request.birthdate = DateUtils.toIso(request.birthdate);

    // Format document
    request.cpf = DocumentUtils.onlyNumbers(request.cpf);

    // Format phone
    request.phone = PhoneUtils.format(request.phone);

    // TEST
    // console.table(request);

    if (this.user) {
      this.userService.update(this.user.id, request)
        .subscribe({
          next: () => {
            this.close.emit(true)
          },
          error: (error) => {
            // console.error('Erro ao editar:', error);
            // this.errorMessage = error.error?.message || 'Não foi possível editar o usuário.';
            this.toastService.error('Não foi possível editar o usuário.');
          }
        });

    } else {

      this.userService.create(request)
        .subscribe({
          next: () => {
            this.close.emit(true)
          },
          error: (error) => {
            // console.error('Erro ao salvar:', error);
            // this.errorMessage = error.error?.message;
            this.toastService.error('Não foi possível salvar o usuário.');
          }
        });
    }

  }

  ngOnChanges(): void {

    if (!this.user) {
      this.form.reset();
      return;
    }

    this.form.patchValue({
      name: this.user.name,
      cpf: this.user.cpf,
      phone: this.user.phone,
      email: this.user.email,

      birthdate: this.user.birthdate,
      //adress
      address: this.user.address,
      number: this.user.number,
      complement: this.user.complement,
      city: this.user.city,
      state: this.user.state,
      zipCode: this.user.zipCode,

    });
  }

  cancel() {
    this.close.emit(false);
  }
}