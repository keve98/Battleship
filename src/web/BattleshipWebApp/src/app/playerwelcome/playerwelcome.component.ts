import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { LoginComponent } from '../login';
import { LoginUser } from '../login/login_user';
import { UserService } from '../user_service';

@Component({
  selector: 'app-playerwelcome',
  templateUrl: './playerwelcome.component.html',
  styleUrls: ['./playerwelcome.component.css']
})
export class PlayerwelcomeComponent {

  username: string | undefined;
  user: LoginUser = new LoginUser;

  constructor(public userService: UserService, public loginComponent : LoginComponent) {
    console.log("welcomecomponent const");
    this.username = loginComponent.username;
    console.log("welcomecomponent const, logincomponent username: " +  loginComponent.username);
  }

}
