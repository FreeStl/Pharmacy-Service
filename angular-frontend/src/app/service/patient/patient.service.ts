import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {Patient} from '../../model/patient';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  baseUrl = 'http://localhost:8080/api/';

  constructor(
    private http: HttpClient
  ) { }

  findByNumber(number: String): Observable<Patient>{
    return this.http.get<Patient>(this.baseUrl + `patients/number/${number}`)
      .pipe(
        tap(() => this.log('Patiend Found')),
        catchError(this.handleError<Patient>('Failed to find the customer', null))
      );
  }

  private handleError<T> (reason = 'No exeption Provided', result?: T) {
    return (error: any): Observable<T> => {
      this.log(reason + ': ' + error.message)
      console.error(reason + ' ' + result);
      return of(result as T);
    };
  }

  private log(message: string) {
    //this.messageService.add(message);
  }
}
