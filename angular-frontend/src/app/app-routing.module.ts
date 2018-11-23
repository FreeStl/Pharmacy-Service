import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SubmitComponent} from "./component/submit/submit.component";
import {InfoComponent} from "./component/info/info.component";

const routes: Routes = [
  {path: 'submit', component: SubmitComponent},
  {path: 'info', component: InfoComponent},

  { path: '**', redirectTo: '#', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
