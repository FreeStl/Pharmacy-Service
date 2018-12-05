import {Observable, of} from 'rxjs';
import {HttpHeaders} from '@angular/common/http';
import {MessageService} from './messages/message.service';
import {Message} from '../model/message';
import {MessageStatus} from '../model/model-enums/messageStatus';

export class MessageAndError{
  protected baseUrl = 'api';
  protected httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  private message: Message = new Message();

  constructor(private messageService: MessageService) { }

   handleError<T> (reason = 'No exception Provided',
                   messageStatus: MessageStatus = MessageStatus.INFO, result?: T) {
    return (error: any): Observable<T> => {
      this.log(reason, messageStatus);
      console.error(reason + ': ' + error.message + ' ' + result);
      return of(result as T);
    };
  }

  log(messageText: string, messageStatus: MessageStatus): void {
    this.message.messageText = messageText;
    this.message.messageStatus = messageStatus;
    this.messageService.add(this.message);
  }
}
