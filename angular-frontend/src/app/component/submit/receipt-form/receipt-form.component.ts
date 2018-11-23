import { Component, OnInit } from '@angular/core';
import {Receipt} from '../../../model/receipt';

@Component({
  selector: 'app-receipt-form',
  templateUrl: './receipt-form.component.html',
  styleUrls: ['./receipt-form.component.scss']
})
export class ReceiptFormComponent implements OnInit {
  newReceipt: Receipt = new Receipt();

  constructor() { }

  ngOnInit() {
  }

}
