import {Component} from '@angular/core';
import {MessageService} from '../../service/messages/message.service';
import {MessageStatus} from '../../model/model-enums/messageStatus';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.scss']
})
export class MessagesComponent{
  infoMessage: MessageStatus = MessageStatus.INFO;
  successMessage: MessageStatus = MessageStatus.SUCCESS;
  errorMessage: MessageStatus = MessageStatus.ERROR;
  warningMessage: MessageStatus = MessageStatus.WARN;
  //showLoader: boolean = false;

  constructor(public messageService: MessageService) { }


}
