import { Component, OnInit } from '@angular/core';
import {Orders} from '../../model/orders';
import {OrdersService} from '../../service/orders/orders.service';

@Component({
  selector: 'app-show-orders',
  templateUrl: './show-orders.component.html',
  styleUrls: ['./show-orders.component.scss']
})
export class ShowOrdersComponent implements OnInit {
  private orders: Orders[];
  private noOrders: boolean = false;

  constructor(private ordersService: OrdersService) { }

  ngOnInit() {
    this.getOrders();
  }

  getOrders(): void {
    this.ordersService.getOrders().subscribe(orders => {
      if (orders == []){
        this.noOrders = true;
      } else{
        this.orders = orders;
      }
    })
  }

}
