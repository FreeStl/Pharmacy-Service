import { Component, OnInit } from '@angular/core';
import {SwitchView} from '../switchView';
import {MedClass} from '../../../model/model-enums/med-class';
import {ComplicatedQueriesService} from '../../../service/complicatedQueries/complicated-queries.service';
import {Medicine} from '../../../model/medicine';

@Component({
  selector: 'app-popular-meds',
  templateUrl: './popular-meds.component.html',
  styleUrls: ['./popular-meds.component.scss']
})
export class PopularMedsComponent extends SwitchView implements OnInit {
  results: Medicine[];
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

  getMedsTop():void {
    this.complicatedQueriesService.getMedsTop(this.medClass)
      .subscribe( results =>{
        this.results = results;
        this.switchToResult = true;
      });

  }

  setMedClass(medClass: MedClass): void {
    this.medClass = medClass;
  }
}
