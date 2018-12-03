import { Component, OnInit } from '@angular/core';
import {SwitchView} from '../switchView';
import {ComplicatedQueriesService} from '../../../service/complicatedQueries/complicated-queries.service';
import {Elements} from '../../../model/elements';

@Component({
  selector: 'app-meds-components',
  templateUrl: './meds-components.component.html',
  styleUrls: ['./meds-components.component.scss']
})
export class MedsComponentsComponent extends SwitchView implements OnInit {
  elements: Elements[];

  constructor(private complQuerService: ComplicatedQueriesService) {
    super()
  }

  ngOnInit() {
  }

  getMedsComponent(): void {
    this.complQuerService.medsComponents().subscribe(
      elements => {
        this.elements = elements;
        this.switchToResult = true;
      }
    )
  }
}
