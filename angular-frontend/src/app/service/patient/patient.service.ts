import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {Patient} from '../../model/patient';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {MessageAndError} from '../MessageAndError';

@Injectable({
  providedIn: 'root'
})
export class PatientService extends MessageAndError {
  constructor(private http: HttpClient) {
    super();
  }

  findByNumber(number: String): Observable<Patient>{
    return this.http.get<Patient>(`${this.baseUrl}/patients/number/?number=${number}`)
      .pipe(
        tap(() => this.log('Patiend Found')),
        catchError(this.handleError<Patient>('Failed to find the customer', null))
      );
  }

}
