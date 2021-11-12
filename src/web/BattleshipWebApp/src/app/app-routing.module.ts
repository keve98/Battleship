import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin';
import { AdminwelcomeComponent } from './adminwelcome';
import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { PlayerComponent } from './player';
import { PlayerwelcomeComponent } from './playerwelcome';
import { RegistrationComponent } from './registration';
import { VerifyNotSuccessfulComponent } from './verify-not-successful/verify-not-successful.component';
import { VerifySuccessfulComponent } from './verify-successful/verify-successful.component';
import { VerifyComponent } from './verify/verify.component';



const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'adminwelcome', component: AdminwelcomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'player', component: PlayerComponent},
  {path: 'welcome', component: PlayerwelcomeComponent},
  {path: 'verify/:code', component: VerifyComponent},
  {path: 'verifySuccessful', component: VerifySuccessfulComponent},
  {path: 'verifyNotSuccessful', component: VerifyNotSuccessfulComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }