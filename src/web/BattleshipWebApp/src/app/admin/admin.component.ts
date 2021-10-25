import { HttpErrorResponse } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { User } from "../user";
import { UserService } from "../user_service"

@Component({ templateUrl: 'admin.component.html' })
export class AdminComponent implements OnInit {

  public users: User[] | undefined;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  public getUsers(): void {

    this.userService.getAllUsers().subscribe(

      (response: User[]) => {
        this.users = response;

        if (this.users?.length == 0) {
          (<HTMLInputElement>document.getElementById('players')).innerText = 'There are no players in the database.'
        } else {
          (<HTMLInputElement>document.getElementById('players')).innerText = '';
        }
      },

      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }






}