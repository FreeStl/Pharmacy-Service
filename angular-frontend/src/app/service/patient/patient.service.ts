import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Patient} from '../../model/patient';
import {HttpClient} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {MessageAndError} from '../MessageAndError';
import {MessageStatus} from '../../model/model-enums/messageStatus';
import {MessageService} from '../messages/message.service';

@Injectable({
  providedIn: 'root'
})
export class PatientService extends MessageAndError {
  constructor(private http: HttpClient, messageService: MessageService) {
    super(messageService);
  }

  findByNumber(number: String): Observable<Patient>{
    return this.http.get<Patient>(`${this.baseUrl}/patients/number/?number=${number}`)
      .pipe(
        tap(() => this.log('Patiend Found',MessageStatus.INFO)),
        catchError(this.handleError<Patient>('Patient not found',MessageStatus.WARN, null))
      );
  }

}
