import { Component, OnInit } from '@angular/core';
import {Patient} from '../../../model/patient';
import {NgForm} from '@angular/forms';
import {PatientService} from '../../../service/patient/patient.service';
import {log} from 'util';

@Component({
  selector: 'app-patient-form',
  templateUrl: './patient-form.component.html',
  styleUrls: ['./patient-form.component.scss']
})
export class PatientFormComponent implements OnInit {
  newPatient: Patient = new Patient();

  constructor(
   private patientService: PatientService
  ) { }

  ngOnInit() {
  }

  checkPatient(): void {
    this.patientService.findByNumber(this.newPatient.number)
      .subscribe(patient =>{
        if (patient != null) {
          this.newPatient = patient;
          const list = document.getElementsByClassName('patient-form');
          for (let i = 0; i < list.length; ++i) {
            list[i].setAttribute('readonly', 'readonly');
          }
        }
    })
  }

}
