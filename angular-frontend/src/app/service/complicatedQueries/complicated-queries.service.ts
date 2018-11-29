import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {MessageAndError} from '../MessageAndError';
import {HttpClient} from '@angular/common/http';
import {MessageService} from '../messages/message.service';
import {catchError} from 'rxjs/operators';
import {MessageStatus} from '../../model/model-enums/messageStatus';
import {MedClass} from '../../model/model-enums/med-class';
import {Medicine} from '../../model/medicine';

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
}
//
