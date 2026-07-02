import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnrollmentModalityFormComponent } from './enrollment-modality-form.component';

describe('EnrollmentModalityFormComponent', () => {
  let component: EnrollmentModalityFormComponent;
  let fixture: ComponentFixture<EnrollmentModalityFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EnrollmentModalityFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EnrollmentModalityFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
