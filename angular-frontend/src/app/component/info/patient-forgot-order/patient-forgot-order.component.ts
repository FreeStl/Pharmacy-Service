import { Component, OnInit } from '@angular/core';
import {ComplicatedQueriesService} from '../../../service/complicatedQueries/complicated-queries.service';
import {MedClass} from '../../../model/model-enums/med-class';
import {SwitchView} from '../switchView';

@Component({
  selector: 'app-patient-forgot-order',
  templateUrl: './patient-forgot-order.component.html',
  styleUrls: ['./patient-forgot-order.component.scss']
})
export class PatientForgotOrderComponent extends SwitchView implements OnInit {
  results: Object[];

  constructor(private complicatedQueriesService: ComplicatedQueriesService) {
    super();
  }

  ngOnInit() {
  }

  getPatientForgotOrder():void {
    this.complicatedQueriesService.GetPatientForgotOrder()
      .subscribe( results =>{
        this.results = results;
        this.switchToResult = true;
      })
  }

}
