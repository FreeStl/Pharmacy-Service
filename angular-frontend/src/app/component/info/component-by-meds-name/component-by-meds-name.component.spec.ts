import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ComponentByMedsNameComponent } from './component-by-meds-name.component';

describe('ComponentByMedsNameComponent', () => {
  let component: ComponentByMedsNameComponent;
  let fixture: ComponentFixture<ComponentByMedsNameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ComponentByMedsNameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ComponentByMedsNameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
