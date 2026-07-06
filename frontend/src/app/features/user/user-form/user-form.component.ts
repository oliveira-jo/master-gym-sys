import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserResponse } from '../../../core/model/response/user-response.model';
import { UserService } from '../../../core/service/user.service';
import { UserRequest } from '../../../core/model/request/user-request.model';
import { DocumentUtils } from '../../../core/utils/DocumentUtils';
import { PhoneUtils } from '../../../core/utils/PhoneUtils';

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

  form = this.fb.group({
    // Data
    name: ['', Validators.required],
    phone: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    cpf: ['', Validators.required],
    password: ['', Validators.required],
  });

  ngOnInit() {
    if (this.user) {
      this.form.patchValue({
        ...this.user,
      });
    }
  }

  save(): void {

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const request: UserRequest = this.form.getRawValue();

    // Format document
    request.cpf = DocumentUtils.onlyNumbers(request.cpf);

    // Format phone
    request.phone = PhoneUtils.format(request.phone);

    if (this.user) {
      this.userService
        .update(this.user.id, request)
        .subscribe(() => this.close.emit(true));

    } else {
      this.userService
        .create(request)
        .subscribe(() => this.close.emit(true));

    }

  }

  ngOnChanges(): void {
    if (!this.user) {
      this.form.reset();
      return;
    }

    this.form.patchValue({
      name: this.user.name,
      phone: this.user.phone,
      email: this.user.email,
      cpf: this.user.cpf,
      password: this.user.password
    });
  }

  cancel() {
    this.close.emit(false);
  }
}