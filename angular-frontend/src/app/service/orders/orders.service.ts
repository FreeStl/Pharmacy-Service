import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Orders} from '../../model/orders';
import {MessageAndError} from '../MessageAndError';
import {HttpClient} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {MessageStatus} from '../../model/model-enums/messageStatus';
import {MessageService} from '../messages/message.service';

@Injectable({
  providedIn: 'root'
})
export class OrdersService extends MessageAndError{

  constructor(private http: HttpClient,messageService: MessageService) {
    super(messageService);
  }

  createOrder(order: Orders): Observable<Orders>{
    return this.http.post<Orders>(`${this.baseUrl}/orders`, order, this.httpOptions)
      .pipe(
        tap(() => this.log('Order Created', MessageStatus.SUCCESS)),
        catchError(this.handleError<Orders>('Failed to create the order',MessageStatus.ERROR, new Orders()))
      );
  }
}
