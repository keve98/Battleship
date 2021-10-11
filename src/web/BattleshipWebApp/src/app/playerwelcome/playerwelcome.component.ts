import {Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user_service';

@Component({
  selector: 'app-playerwelcome',
  templateUrl: './playerwelcome.component.html',
  styleUrls: ['./playerwelcome.component.css']
})
export class PlayerwelcomeComponent implements OnInit {

  username : string = "";
  
  constructor(public userService: UserService, public router: Router) {
    this.username = sessionStorage.getItem('loggedUser')!;
  }

  ngOnInit(): void {
    this.username = sessionStorage.getItem('loggedUser')!;
  }

  navigateToPlayerData(): void
  {
    this.router.navigate([`/player`]);
  }

}
