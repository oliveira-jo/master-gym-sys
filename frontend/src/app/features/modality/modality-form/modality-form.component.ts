import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ModalityResponse } from '../../../core/model/modality-response.model';
import { ModalityService } from '../../../core/service/modality.service';
import { ModalityRequest } from '../../../core/model/modality-request.model';

@Component({
  selector: 'app-modality-form',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './modality-form.component.html',
  styleUrl: './modality-form.component.css'
})
export class ModalityFormComponent {
  @Input() modality?: ModalityResponse;
  @Output() close = new EventEmitter<boolean>();

  private fb = inject(NonNullableFormBuilder);
  private modalityService = inject(ModalityService);

  form = this.fb.group({
    // Data
    name: ['', Validators.required],

  });

  ngOnInit() {
    if (this.modality) {
      this.form.patchValue({
        ...this.modality,
      });
    }
  }

  save(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const request: ModalityRequest = this.form.getRawValue();


    if (this.modality) {
      this.modalityService
        .update(this.modality.id, request)
        .subscribe(() => this.close.emit(true));

    } else {
      this.modalityService
        .create(request)
        .subscribe(() => this.close.emit(true));

    }

  }

  ngOnChanges(): void {
    if (!this.modality) {
      this.form.reset();
      return;
    }

    this.form.patchValue({
      name: this.modality.name,
    });
  }

  cancel() {
    this.close.emit(false);
  }
}