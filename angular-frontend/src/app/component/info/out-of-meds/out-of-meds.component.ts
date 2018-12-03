import { Component, OnInit } from '@angular/core';
import {SwitchView} from '../switchView';
import {MedClass} from '../../../model/model-enums/med-class';
import {Medicine} from '../../../model/medicine';
import {ComplicatedQueriesService} from '../../../service/complicatedQueries/complicated-queries.service';

@Component({
  selector: 'app-out-of-meds',
  templateUrl: './out-of-meds.component.html',
  styleUrls: ['./out-of-meds.component.scss']
})
export class OutOfMedsComponent extends SwitchView implements OnInit {
  private critAmount = 5;

  medicines: Medicine[];

  medClass: MedClass = null;
  medClassPills: MedClass = MedClass.PILLS;
  medClassPowder: MedClass = MedClass.POWDER;
  medClassOintment: MedClass = MedClass.OINTMENT;
  medClassLiquid: MedClass = MedClass.LIQUID;

  constructor(private complicatedQueriesService: ComplicatedQueriesService) {
    super()
  }

  ngOnInit() {
  }

  setMedClass(medClass: MedClass): void {
    this.medClass = medClass;
  }

  outOfMeds(): void {
    this.complicatedQueriesService.outOfMeds(this.medClass, this.critAmount)
      .subscribe(medicines => {
        this.medicines = medicines;
        this.switchToResult = true;
      })
  }
}
