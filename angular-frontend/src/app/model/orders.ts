import {Doctor} from './doctor';
import {Patient} from './patient';
import {Receipt} from './receipt';
import {Medicine} from './medicine';
import {OrderStatus} from './model-enums/order-status';

export class Orders {
  createdAt: Date;
  updatedAt: Date;
  id: number;
  doctor: Doctor;
  patient: Patient;
  receipt: Receipt;
  medicine: Medicine;
  orderStatus: OrderStatus;


}
