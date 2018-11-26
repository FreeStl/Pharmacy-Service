import {Injectable} from '@angular/core';
import {Message} from '../../model/message';

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
    return this.message;
  }

  public clear(): void {
    this.message = new Message();
  }
}
