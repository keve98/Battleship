import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user_service';

@Component({
  selector: 'app-adminwelcome',
  templateUrl: './adminwelcome.component.html',
  styleUrls: ['./adminwelcome.component.css']
})
export class AdminwelcomeComponent implements OnInit {

  username : string = "";
  users: User[] | undefined | null;
  
  constructor(public userService: UserService, public router: Router) {
    this.username = sessionStorage.getItem('loggedUser')!;
  }

  ngOnInit(): void {
    this.username = sessionStorage.getItem('loggedUser')!;
  }


  displayUsers(){
    this.userService.getAllUsers().subscribe(
      (u: User[]) =>{
        this.users = u;
        if(this.users.length == 0){
          alert("There are no users in the database.")
        }
    })
  }

  async searchForUser(){
    var tmp : User[];
    this.users = tmp!;
    var searchUser = (<HTMLInputElement>document.getElementById('searchUser')).value;
    await (await this.userService.searchUsernames(searchUser)).subscribe(
      (u: User[]) =>{
        this.users = u;
        if(this.users.length == 0){
          alert("There are no users in the database with the name of: " + searchUser)
        }
      }
    )
  }


}
