import { Component, OnInit } from '@angular/core';
import {Doctor} from '../../../model/doctor';

@Component({
  selector: 'app-doctor-form',
  templateUrl: './doctor-form.component.html',
  styleUrls: ['./doctor-form.component.scss']
})
export class DoctorFormComponent implements OnInit {
  newDoctor: Doctor = new Doctor();


  constructor() { }

  ngOnInit() {
  }



}
