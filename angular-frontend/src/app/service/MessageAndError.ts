import {Observable, of} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';

export class MessageAndError {
  protected baseUrl = 'http://localhost:8080/api';
  protected httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }
  constructor() { }

   handleError<T> (reason = 'No exeption Provided', result?: T) {
    return (error: any): Observable<T> => {
      this.log(reason + ': ' + error.message)
      console.error(reason + ' ' + result);
      return of(result as T);
    };
  }

  log(message: string) {
    //this.messageService.add(message);
  }
}
