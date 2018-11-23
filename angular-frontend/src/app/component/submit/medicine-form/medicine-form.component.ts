import { Component, OnInit } from '@angular/core';
import { Component, OnInit } from '@angular/core';

import { Observable, Subject } from 'rxjs';

import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';

import {Medicine} from '../../../model/medicine';
import {MedicineService} from '../../../service/medicine/medicine.service';

@Component({
  selector: 'app-medicine-form',
  templateUrl: './medicine-form.component.html',
  styleUrls: ['./medicine-form.component.scss']
})
export class MedicineFormComponent implements OnInit {
  medicines$: Observable<Medicine[]>;
  selectedMedicine: Medicine = new Medicine();
  private searchTerms = new Subject<string>();

  constructor(private medicineService: MedicineService) { }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  addMedicine(medicine: Medicine): void {
    this.selectedMedicine = medicine;
    this.medicines$= new Observable<Medicine[]>();
  }

  ngOnInit(): void {
    this.medicines$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.medicineService.searchMedicine(term))
    )
  }

}
