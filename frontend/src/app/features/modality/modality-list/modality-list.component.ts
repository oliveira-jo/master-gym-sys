import { Component, inject, OnInit } from '@angular/core';
import { ModalityService } from '../../../core/service/modality.service';
import { ModalityResponse } from '../../../core/model/modality-response.model';
import { PageResponse } from '../../../core/model/page/page-response.model';
import { PageableRequest } from '../../../core/model/page/pageable-request.model';
import { FormBuilder } from '@angular/forms';
import { ModalityRequest } from '../../../core/model/modality-request.model';
import { ModalityFormComponent } from "../modality-form/modality-form.component";

@Component({
  selector: 'app-modality-list',
  imports: [ModalityFormComponent],
  templateUrl: './modality-list.component.html',
  styleUrl: './modality-list.component.css'
})
export class ModalityListComponent implements OnInit {

  private readonly modalityService = inject(ModalityService);

  modalities: ModalityResponse[] = [];

  pageResponse?: PageResponse<ModalityResponse>;

  page = new PageableRequest(0, 10, 'id');

  totalElements = 0;
  selectedModality?: ModalityResponse;
  modalOpen = false;

  // 
  private fb = inject(FormBuilder);
  searchForm = this.fb.group({
    name: ['']
  });


  ngOnInit(): void {
    this.loadModalities();
  }

  loadModalities(): void {
    const filter: ModalityRequest = {
      name: this.searchForm.value.name ?? ''
    };

    this.modalityService
      .findAll(this.page)
      .subscribe({

        next: page => {
          this.modalities = page.content;
          this.totalElements = page.totalElements;
        },

        error: err => console.error(err)

      });
  }

  search(): void {

    this.page.page = 0;
    this.loadModalities();

  }

  clear(): void {
    this.searchForm.reset();
    this.page.page = 0;
    this.loadModalities();
  }

  openCreate(): void {
    this.selectedModality = undefined;
    this.modalOpen = true;
  }

  openEdit(modality: ModalityResponse): void {
    this.selectedModality = modality;
    this.modalOpen = true;
  }

  delete(id: number): void {

    if (!confirm('Deseja realmente excluir este modalidade?')) {
      return;
    }
    this.modalityService.delete(id).subscribe({
      next: () => {
        this.loadModalities();
      },
      error: err => console.error(err)

    });

  }

  closeModal(refresh: boolean): void {
    this.modalOpen = false;
    if (refresh) {
      this.loadModalities();
    }
  }

}
