import { Component, OnInit } from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {debounceTime, distinctUntilChanged, map, switchMap} from 'rxjs/operators';
import {MedicineService} from '../../../service/medicine/medicine.service';
import {Medicine} from '../../../model/medicine';
import {log} from "util";
import {NgbCalendar, NgbDate} from '@ng-bootstrap/ng-bootstrap';
import {SwitchView} from '../switchView';
import {ComplicatedQueriesService} from '../../../service/complicatedQueries/complicated-queries.service';

const states = ['Alabama', 'Alaska', 'American Samoa', 'Arizona', 'Arkansas', 'California', 'Colorado',
  'Connecticut', 'Delaware', 'District Of Columbia', 'Federated States Of Micronesia', 'Florida', 'Georgia',
  'Guam', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine',
  'Marshall Islands', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana',
  'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota',
  'Northern Mariana Islands', 'Ohio', 'Oklahoma', 'Oregon', 'Palau', 'Pennsylvania', 'Puerto Rico', 'Rhode Island',
  'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virgin Islands', 'Virginia',
  'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'];

@Component({
  selector: 'app-meds-usage',
  templateUrl: './meds-usage.component.html',
  styleUrls: ['./meds-usage.component.scss']
})
export class MedsUsageComponent extends SwitchView implements OnInit {
  medicines$: Observable<Medicine[]>;
  searchTerms = new Subject<string>();
  selectedMedicine: Medicine = new Medicine();
  showDate: false;
  hoveredDate: NgbDate;
  fromDate: NgbDate;
  toDate: NgbDate;
  resultCount: number;

  constructor(private medicineService: MedicineService,
              private complQuerService: ComplicatedQueriesService,
              private calendar: NgbCalendar) {
    super();
    this.fromDate = calendar.getPrev(calendar.getToday(), 'd', 30);
    this.toDate = calendar.getToday();
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

  medsUsage(): void {
    const from: Date = new Date(this.fromDate.year, this.fromDate.month -1, this.fromDate.day);
    const to: Date = new Date(this.toDate.year, this.toDate.month -1, this.toDate.day);

    this.complQuerService.medsUsage(this.selectedMedicine.name, from, to)
      .subscribe( result => {
        this.resultCount = result;
        this.switchToResult = true;
        }
      )
  }

}
