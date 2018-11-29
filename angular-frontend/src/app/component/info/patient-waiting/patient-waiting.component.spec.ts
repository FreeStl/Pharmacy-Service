import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientWaitingComponent } from './patient-waiting.component';

describe('PatientWaitingComponent', () => {
  let component: PatientWaitingComponent;
  let fixture: ComponentFixture<PatientWaitingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PatientWaitingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PatientWaitingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
