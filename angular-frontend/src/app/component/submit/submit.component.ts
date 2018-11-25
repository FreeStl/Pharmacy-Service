import {AfterViewChecked, AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import { Router } from '@angular/router';

import {DoctorFormComponent} from './doctor-form/doctor-form.component';
import {PatientFormComponent} from './patient-form/patient-form.component';
import {Medicine} from '../../model/medicine';
import {MedicineFormComponent} from './medicine-form/medicine-form.component';
import {ReceiptFormComponent} from './receipt-form/receipt-form.component';
import {Doctor} from '../../model/doctor';
import {Patient} from '../../model/patient';
import {Receipt} from '../../model/receipt';
import {Orders} from '../../model/orders';
import {OrderStatus} from '../../model/model-enums/order-status';
import {OrdersService} from '../../service/orders/orders.service';
import {MedType} from '../../model/model-enums/med-type';
import {log} from 'util';

@Component({
  selector: 'app-submit',
  templateUrl: './submit.component.html',
  styleUrls: ['./submit.component.scss']
})
export class SubmitComponent implements OnInit, AfterViewChecked {
  order: Orders = new Orders();

  @ViewChild(DoctorFormComponent) doctorFormComponent;
  @ViewChild(PatientFormComponent) patientFormComponent;
  @ViewChild(MedicineFormComponent) medicineFormComponent;
  @ViewChild(ReceiptFormComponent) receiptFormComponent;

  constructor(private ordersService: OrdersService,
              private router: Router) { }

  ngAfterViewChecked(){
    this.order.doctor = this.doctorFormComponent.newDoctor;
    this.order.patient = this.patientFormComponent.newPatient;
    this.order.medicine = this.medicineFormComponent.selectedMedicine;
    this.order.receipt = this.receiptFormComponent.newReceipt;
  }

  createOrder(): void {
    this.order.orderStatus = OrderStatus.READY;

    if(this.order.medicine.medType == MedType.PRODUCED){
      for (let element of this.order.medicine.elements){
        if (element.amount == 0){
          this.order.orderStatus = OrderStatus.IN_PROD;
          break;
        }
      }
    }

    this.ordersService.createOrder(this.order).subscribe( order =>{
      if (order != new Orders()){
        this.router.navigateByUrl('/#')
      }
    })
  }

  ngOnInit() {}
}
