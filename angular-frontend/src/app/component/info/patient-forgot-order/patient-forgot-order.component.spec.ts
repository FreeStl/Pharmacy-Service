import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientForgotOrderComponent } from './patient-forgot-order.component';

describe('PatientForgotOrderComponent', () => {
  let component: PatientForgotOrderComponent;
  let fixture: ComponentFixture<PatientForgotOrderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PatientForgotOrderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientForgotOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
