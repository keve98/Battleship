import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { CommonModule } from '@angular/common'; 
import { AppComponent } from './app.component';
import { UserService } from './user_service';
import { LoginComponent } from './login';
import { AdminComponent } from './admin';
import { PlayerComponent } from './player';
import { PlayerwelcomeComponent } from './playerwelcome/playerwelcome.component';
import { AdminwelcomeComponent } from './adminwelcome/adminwelcome.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminComponent,
    PlayerComponent,
    PlayerwelcomeComponent,
    AdminwelcomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
