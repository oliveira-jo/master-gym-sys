import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPaymentComponent } from './search-payment.component';

describe('SearchPaymentComponent', () => {
  let component: SearchPaymentComponent;
  let fixture: ComponentFixture<SearchPaymentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchPaymentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchPaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
