import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import {DoctorFormComponent} from './doctor-form/doctor-form.component';
import {PatientFormComponent} from './patient-form/patient-form.component';
import {Medicine} from '../../model/medicine';
import {MedicineFormComponent} from './medicine-form/medicine-form.component';
import {ReceiptFormComponent} from './receipt-form/receipt-form.component';
import {Doctor} from '../../model/doctor';
import {Patient} from '../../model/patient';
import {Receipt} from '../../model/receipt';
import {Component} from '../../model/';

@Component({
  selector: 'app-submit',
  templateUrl: './submit.component.html',
  styleUrls: ['./submit.component.scss']
})
export class SubmitComponent implements OnInit {
  doctor: Doctor;
  patient: Patient;
  medicine: Medicine;
  receipt: Receipt;
  component: Component;

  @ViewChild(DoctorFormComponent) doctorFormComponent;
  @ViewChild(PatientFormComponent) patientFormComponent;
  @ViewChild(MedicineFormComponent) medicineFormComponent;
  @ViewChild(ReceiptFormComponent) receiptFormComponent;

  constructor() { }

  ngOnInit() {
  }
  ngAfterViewInit() {
    this.message = this.child.message
  }

}
