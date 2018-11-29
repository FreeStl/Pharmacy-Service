import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SubmitComponent} from "./component/submit/submit.component";
import {InfoComponent} from "./component/info/info.component";
import {ShowOrdersComponent} from './component/show-orders/show-orders.component';

const routes: Routes = [
  {path: 'submit', component: SubmitComponent},
  {path: 'info', component: InfoComponent},
  {path: '', component: ShowOrdersComponent},
  { path: '**', redirectTo: '#', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
