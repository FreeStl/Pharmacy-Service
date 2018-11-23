import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {Medicine} from '../../model/medicine';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {MessageAndError} from '../MessageAndError';


@Injectable({
  providedIn: 'root'
})
export class MedicineService extends MessageAndError{

  constructor(private http: HttpClient) {
    super();
  }

  searchMedicine(term: string): Observable<Medicine[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<Medicine[]>(`${this.baseUrl}/medicines/?name=${term}`).pipe(
      tap(_ => this.log(`found medicine matching "${term}"`)),
      catchError(this.handleError<Medicine[]>('Failed to find medicine', []))
    );
  }

}
