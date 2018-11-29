import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PopularMedsComponent } from './popular-meds.component';

describe('PopularMedsComponent', () => {
  let component: PopularMedsComponent;
  let fixture: ComponentFixture<PopularMedsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PopularMedsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PopularMedsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
