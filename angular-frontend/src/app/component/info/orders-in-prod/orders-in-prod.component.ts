import { Component, OnInit } from '@angular/core';
import {SwitchView} from '../switchView';
import {ComplicatedQueriesService} from '../../../service/complicatedQueries/complicated-queries.service';

@Component({
  selector: 'app-orders-in-prod',
  templateUrl: './orders-in-prod.component.html',
  styleUrls: ['./orders-in-prod.component.scss']
})
export class OrdersInProdComponent extends SwitchView implements OnInit {
  orders: Object[];

  constructor(private complQuerService: ComplicatedQueriesService) {
    super()
  }

  ngOnInit() {
  }

  getOrdersInProd(): void {
    this.complQuerService.ordersInProd().subscribe(
      orders => {
        this.orders = orders;
        this.switchToResult = true;
      }
    );
  }

}
