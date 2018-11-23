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

@NgModule({
  declarations: [
    AppComponent,
    SubmitComponent,
    InfoComponent,
    DoctorFormComponent,
    PatientFormComponent,
    MedicineFormComponent,
    ReceiptFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    PatientService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
