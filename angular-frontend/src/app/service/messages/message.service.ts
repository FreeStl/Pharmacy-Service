import {Injectable} from '@angular/core';
import {Message} from '../../model/message';
import {log} from 'util';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private message: Message = new Message();

  constructor() { }

  public add(message: Message): void {
    this.message = message;
  }

  public getMessage(): Message {
    log('MESSAGE in get: ' + this.message.messageText +', STATUS in get: ' + this.message.messageStatus);
    return this.message;
  }

  public clear(): void {
    this.message = new Message();
  }
}
