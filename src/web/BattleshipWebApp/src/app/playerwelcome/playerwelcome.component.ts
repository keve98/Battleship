import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { LoginComponent } from '../login';
import { LoginUser } from '../login/login_user';
import { User } from '../user';
import { UserService } from '../user_service';

@Component({
  selector: 'app-playerwelcome',
  templateUrl: './playerwelcome.component.html',
  styleUrls: ['./playerwelcome.component.css']
})
export class PlayerwelcomeComponent implements OnInit {

  username: string | undefined;
  public user : LoginUser = new LoginUser;
  
  constructor(public userService: UserService, public loginComponent : LoginComponent) {
  }
  ngOnInit(): void {
    this.username = this.userService.loggedInUser.username;
  }

}
