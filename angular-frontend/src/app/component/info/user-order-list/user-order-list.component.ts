import { Component, OnInit } from '@angular/core';
import {Patient} from '../../../model/patient';
import {MedClass} from '../../../model/model-enums/med-class';
import {MedicineService} from '../../../service/medicine/medicine.service';
import {ComplicatedQueriesService} from '../../../service/complicatedQueries/complicated-queries.service';
import {NgbCalendar, NgbDate} from '@ng-bootstrap/ng-bootstrap';
import {SwitchView} from '../switchView';
import {Observable, Subject} from 'rxjs';
import {Medicine} from '../../../model/medicine';
import {debounceTime, distinctUntilChanged, switchMap} from 'rxjs/operators';

@Component({
  selector: 'app-user-order-list',
  templateUrl: './user-order-list.component.html',
  styleUrls: ['./user-order-list.component.scss']
})
export class UserOrderListComponent extends SwitchView implements OnInit {
  patients: Patient[];

  medClass: MedClass = null;
  medClassPills: MedClass = MedClass.PILLS;
  medClassPowder: MedClass = MedClass.POWDER;
  medClassOintment: MedClass = MedClass.OINTMENT;
  medClassLiquid: MedClass = MedClass.LIQUID;

  medicines$: Observable<Medicine[]>;
  searchTerms = new Subject<string>();
  selectedMedicine: Medicine = new Medicine();

  hoveredDate: NgbDate;
  fromDate: NgbDate;
  toDate: NgbDate;

  showDate: false;

  constructor(private medicineService: MedicineService,
              private complQuerService: ComplicatedQueriesService,
              private calendar: NgbCalendar) {
    super();
    this.fromDate = calendar.getPrev(calendar.getToday(), 'd', 30);
    this.toDate = calendar.getToday();
  }

  setMedClass(medClass: MedClass): void {
    this.medClass = medClass;
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
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.medicineService.searchMedicine(term))
    )
  }

  onDateSelection(date: NgbDate) {
    if (!this.fromDate && !this.toDate) {
      this.fromDate = date;
    } else if (this.fromDate && !this.toDate && date.after(this.fromDate)) {
      this.toDate = date;
      this.showDate = false;
    } else {
      this.toDate = null;
      this.fromDate = date;
    }
  }

  isHovered(date: NgbDate) {
    return this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    return date.after(this.fromDate) && date.before(this.toDate);
  }

  isRange(date: NgbDate) {
    return date.equals(this.fromDate) || date.equals(this.toDate) || this.isInside(date) || this.isHovered(date);
  }

  userOrderList(): void {
    const from: Date = new Date(this.fromDate.year, this.fromDate.month -1, this.fromDate.day);
    const to: Date = new Date(this.toDate.year, this.toDate.month -1, this.toDate.day);

    this.complQuerService.userOrderList(this.selectedMedicine.name, this.medClass, from, to)
      .subscribe( patients => {
          this.patients = patients;
          this.switchToResult = true;
        }
      )
  }



}
//userOrderList
