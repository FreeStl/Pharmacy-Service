import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdersInProdComponent } from './orders-in-prod.component';

describe('OrdersInProdComponent', () => {
  let component: OrdersInProdComponent;
  let fixture: ComponentFixture<OrdersInProdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrdersInProdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdersInProdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
