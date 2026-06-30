import { Component, inject, OnInit } from '@angular/core';
import { SubscriptionResponse } from '../../../core/model/subscription-response.model';
import { SubscriptionService } from '../../../core/service/subscription.service';
import { PageResponse } from '../../../core/model/page/page-response.model';
import { PageableRequest } from '../../../core/model/page/pageable-request.model';
import { FormBuilder } from '@angular/forms';
import { SubscriptionFormComponent } from "../subscription-form/subscription-form.component";

@Component({
  selector: 'app-subscription-list',
  imports: [SubscriptionFormComponent],
  templateUrl: './subscription-list.component.html',
  styleUrl: './subscription-list.component.css'
})
export class SubscriptionListComponent implements OnInit {

  private readonly subscriptionService = inject(SubscriptionService);

  subscriptions: SubscriptionResponse[] = [];

  pageResponse?: PageResponse<SubscriptionResponse>;

  page = new PageableRequest(0, 10, 'id');

  selectedSubscription?: SubscriptionResponse;

  totalElements = 0;

  modalOpen = false;

  private fb = inject(FormBuilder);
  searchForm = this.fb.group({
    name: [''],
    modality: [''],
    price: [''],
    active: [''],
  });

  ngOnInit(): void {
    this.loadSubscriptions();
  }

  loadSubscriptions(): void {
    this.subscriptionService
      .findAll(this.page)
      .subscribe({

        next: page => {

          //pagination
          this.pageResponse = page;

          this.subscriptions = page.content;
          this.totalElements = page.totalElements;
        },

        error: err => console.error(err)

      });
  }

  search(): void {
    this.page.page = 0;
    this.loadSubscriptions();
  }

  clear(): void {
    this.searchForm.reset();
    this.page.page = 0;
    this.loadSubscriptions();
  }

  openCreate(): void {
    this.selectedSubscription = undefined;
    this.modalOpen = true;
  }

  openEdit(subscription: SubscriptionResponse): void {
    this.selectedSubscription = subscription;
    this.modalOpen = true;
  }

  delete(id: number): void {
    if (!confirm('Deseja realmente excluir este plano?')) {
      return;
    }
    this.subscriptionService.delete(id).subscribe({
      next: () => {
        this.loadSubscriptions();
      },
      error: err => console.error(err)

    });
  }

  closeModal(refresh: boolean): void {
    this.modalOpen = false;
    if (refresh) {
      this.loadSubscriptions();
    }
  }


  //pagination
  nextPage(): void {
    if (!this.pageResponse?.last) {
      this.page.page++;
      this.loadSubscriptions();
    }
  }

  previousPage(): void {
    if (!this.pageResponse?.first) {
      this.page.page--;
      this.loadSubscriptions();
    }

  }

  goToPage(page: number): void {
    this.page.page = page;
    this.loadSubscriptions();
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
