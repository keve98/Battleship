import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user_service';

@Component({
  selector: 'app-adminwelcome',
  templateUrl: './adminwelcome.component.html',
  styleUrls: ['./adminwelcome.component.css']
})
export class AdminwelcomeComponent implements OnInit {

  username : string = "";
  
  constructor(public userService: UserService, public router: Router) {
    this.username = sessionStorage.getItem('loggedUser')!;
  }

  ngOnInit(): void {
    this.username = sessionStorage.getItem('loggedUser')!;
  }


}
