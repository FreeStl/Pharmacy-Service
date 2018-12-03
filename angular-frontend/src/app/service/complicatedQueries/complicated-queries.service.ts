import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import {MessageAndError} from '../MessageAndError';
import {HttpClient} from '@angular/common/http';
import {MessageService} from '../messages/message.service';
import {catchError} from 'rxjs/operators';
import {MessageStatus} from '../../model/model-enums/messageStatus';
import {MedClass} from '../../model/model-enums/med-class';
import {Medicine} from '../../model/medicine';
import {Patient} from '../../model/patient';

@Injectable({
  providedIn: 'root'
})
export class ComplicatedQueriesService extends MessageAndError{

  constructor(private http: HttpClient,messageService: MessageService) {
    super(messageService);
  }

  GetPatientForgotOrder(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/patientForgotOrder`)
      .pipe(
        catchError(this.handleError('Nothing found',MessageStatus.ERROR, []))
      )
  }

  GetPatientWaiting(medClass: MedClass): Observable<any[]> {
    let path: string;

    if (medClass == null){
      path = '';
    } else {
      let medClassStr: string = medClass.toString();
      path = `?class=${medClassStr}`;
    }
    return this.http.get<any[]>(`${this.baseUrl}/patientWaiting/${path}`)
      .pipe(
        catchError(this.handleError('Nothing found',MessageStatus.ERROR, []))
      )
  }

  getMedsTop(medClass: MedClass): Observable<Medicine[]> {
    let path: string;

    if (medClass == null){
      path = '';
    } else {
      let medClassStr: string = medClass.toString();
      path = `?class=${medClassStr}`;
    }
    return this.http.get<Medicine[]>(`${this.baseUrl}/popularMedsTop/${path}`)
      .pipe(
        catchError(this.handleError('Nothing found',MessageStatus.ERROR, []))
      )
  }

  medsUsage(name: string, from: Date, to: Date): Observable<number> {
    if (name == null || !name.trim()) {
      return of(0);
    }
    return this.http.get<number>(`${this.baseUrl}/medsUsedForPeriod/?name=${name}`
      + `&from=${from.getTime()}&to=${to.getTime()}`)
      .pipe(
      catchError(this.handleError('Failed to find amount', MessageStatus.ERROR, 0))
    );
  }

  userOrderList(name: string, medClass: MedClass, from: Date, to: Date): Observable<Patient[]> {
    if (name == null || !name.trim()) {
      return of([]);
    }
    return this.http.get<Patient[]>(`${this.baseUrl}/userOrderList/?name=${name}&class=${medClass}`
      + `&from=${from.getTime()}&to=${to.getTime()}`)
      .pipe(
        catchError(this.handleError('Failed to find patients', MessageStatus.ERROR, []))
      );
  }
}
//userOrderList
