import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedsUsageComponent } from './meds-usage.component';

describe('MedsUsageComponent', () => {
  let component: MedsUsageComponent;
  let fixture: ComponentFixture<MedsUsageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedsUsageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedsUsageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
