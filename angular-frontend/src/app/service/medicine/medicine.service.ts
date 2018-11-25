import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Medicine} from '../../model/medicine';
import {HttpClient} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {MessageAndError} from '../MessageAndError';
import {MessageStatus} from '../../model/model-enums/messageStatus';
import {MessageService} from '../messages/message.service';


@Injectable({
  providedIn: 'root'
})
export class MedicineService extends MessageAndError{

  constructor(private http: HttpClient, messageService: MessageService) {
    super(messageService);
  }

  searchMedicine(term: string): Observable<Medicine[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<Medicine[]>(`${this.baseUrl}/medicines/?name=${term}`).pipe(
      //tap(_ => this.log(`Found medicine matching "${term}"`, MessageStatus.INFO)),
      //catchError(this.handleError<Medicine[]>('Failed to find medicine', MessageStatus.ERROR, []))
    );
  }

}
