import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { UserService } from './user_service';
import { LoginComponent } from './login';
import { AdminComponent } from './admin';
//import { PlayerComponent } from './player';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminComponent//,
   // PlayerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
