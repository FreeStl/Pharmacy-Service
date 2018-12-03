import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedsComponentsComponent } from './meds-components.component';

describe('MedsComponentsComponent', () => {
  let component: MedsComponentsComponent;
  let fixture: ComponentFixture<MedsComponentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedsComponentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedsComponentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
