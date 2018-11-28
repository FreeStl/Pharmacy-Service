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
  private page = 1;
  private totalElements = 0;
  private pageSize = 10;

  constructor(private ordersService: OrdersService) { }

  ngOnInit() {
    this.getOrders();
  }

  getOrders(): void {
    let pageToSend = this.page - 1;
    this.ordersService.getOrders(pageToSend, this.pageSize).subscribe(data => {
        this.orders = data['content'];
        this.totalElements = data['totalElements'];
        this.page = data['number']+1;
        this.pageSize = data['size'];
    })
  }

  changePage(page: any): void{
    this.page = page;
    this.getOrders();
  }

  changePageSize(size: number): void{
    this.pageSize = size;
    this.getOrders();
  }

}
