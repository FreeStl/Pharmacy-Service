import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { HttpClientModule }    from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SubmitComponent } from './component/submit/submit.component';
import { InfoComponent } from './component/info/info.component';
import { DoctorFormComponent } from './component/submit/doctor-form/doctor-form.component';
import { PatientFormComponent } from './component/submit/patient-form/patient-form.component';
import { MedicineFormComponent } from './component/submit/medicine-form/medicine-form.component';
import { ReceiptFormComponent } from './component/submit/receipt-form/receipt-form.component';
import {PatientService} from './service/patient/patient.service';
import {MedicineService} from './service/medicine/medicine.service';
import {OrdersService} from './service/orders/orders.service';
import {MessageService} from './service/messages/message.service';
import { MessagesComponent } from './component/messages/messages.component';
import { PatientForgotOrderComponent } from './component/info/patient-forgot-order/patient-forgot-order.component';
import { ShowOrdersComponent } from './component/show-orders/show-orders.component';

@NgModule({
  declarations: [
    AppComponent,
    SubmitComponent,
    InfoComponent,
    DoctorFormComponent,
    PatientFormComponent,
    MedicineFormComponent,
    ReceiptFormComponent,
    MessagesComponent,
    PatientForgotOrderComponent,
    ShowOrdersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    PatientService,
    MedicineService,
    OrdersService,
    MessageService,

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
