import { Component, OnInit } from '@angular/core';
import {Elements} from '../../../model/elements';
import {ComplicatedQueriesService} from '../../../service/complicatedQueries/complicated-queries.service';
import {SwitchView} from '../switchView';
import {Observable, Subject} from 'rxjs';
import {Medicine} from '../../../model/medicine';
import {debounceTime, distinctUntilChanged, switchMap} from 'rxjs/operators';
import {MedicineService} from '../../../service/medicine/medicine.service';

@Component({
  selector: 'app-component-by-meds-name',
  templateUrl: './component-by-meds-name.component.html',
  styleUrls: ['./component-by-meds-name.component.scss']
})
export class ComponentByMedsNameComponent extends SwitchView implements OnInit {
  elements: Elements[];

  medicines$: Observable<Medicine[]>;
  searchTerms = new Subject<string>();
  selectedMedicine: Medicine = new Medicine();

  constructor(private complQuerService: ComplicatedQueriesService,
              private medicineService: MedicineService
  ) {
    super()
  }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  chooseMeds(medicine: Medicine): void {
    this.selectedMedicine = medicine;
    this.searchTerms.next('');
  }

  ngOnInit(): void {
    this.medicines$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.medicineService.searchMedicine(term))
    )
  }

  getMedsComponent(): void {
    this.complQuerService.componentInfoByMedsName(this.selectedMedicine.id).subscribe(
      elements => {
        this.elements = elements;
        this.switchToResult = true;
      }
    )
  }
}
