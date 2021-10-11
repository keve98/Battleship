import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { LoginComponent } from './login';
import { UserService } from './user_service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{


  constructor(private loginComponent: LoginComponent, private router: Router, private userService: UserService){}
 

  public get authenticated(): boolean {
    return this.loginComponent.isAuthenticated;
}


  public logout(){
    sessionStorage.removeItem('loggedUser');
    this.router.navigate([`/login`]);
    this.loginComponent.isAuthenticated = false;
    this.userService.logout();
  }
}
