import {Component, OnInit} from '@angular/core';
import {MedClass} from '../../../model/model-enums/med-class';
import {ComplicatedQueriesService} from '../../../service/complicatedQueries/complicated-queries.service';
import {SwitchView} from '../switchView';

@Component({
  selector: 'app-patient-waiting',
  templateUrl: './patient-waiting.component.html',
  styleUrls: ['./patient-waiting.component.scss']
})
export class PatientWaitingComponent extends SwitchView implements OnInit {
  results: Object[];
  medClass: MedClass = null;

  medClassPills: MedClass = MedClass.PILLS;
  medClassPowder: MedClass = MedClass.POWDER;
  medClassOintment: MedClass = MedClass.OINTMENT;
  medClassLiquid: MedClass = MedClass.LIQUID;


  constructor(private complicatedQueriesService: ComplicatedQueriesService) {
    super();
  }

  ngOnInit() {
  }

  getPatientWaiting():void {
      this.complicatedQueriesService.GetPatientWaiting(this.medClass)
        .subscribe( results =>{
          this.results = results;
          this.switchToResult = true;
        });

  }

  setMedClass(medClass: MedClass): void {
    this.medClass = medClass;
  }
}
