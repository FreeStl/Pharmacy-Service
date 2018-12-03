import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OutOfMedsComponent } from './out-of-meds.component';

describe('OutOfMedsComponent', () => {
  let component: OutOfMedsComponent;
  let fixture: ComponentFixture<OutOfMedsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OutOfMedsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OutOfMedsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
