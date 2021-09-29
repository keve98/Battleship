import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin';
import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { PlayerComponent } from './player';
import { RegistrationComponent } from './registration';


const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'home', component: HomeComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'player', component: PlayerComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }