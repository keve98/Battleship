import { Component, Injectable, OnInit } from "@angular/core";
import { User } from "../user";
import { UserService } from "../user_service";
import { LoginComponent } from "../login";


@Injectable({
    providedIn: 'root'
})
@Component({ templateUrl: 'player.component.html' })
export class PlayerComponent implements OnInit{

   public user : User = new User;

    constructor(private userService: UserService, private loginComponent: LoginComponent){
    }


    ngOnInit(): void {
       /* this.user.username = this.loginComponent.currentUser?.username;
        this.user.name = this.loginComponent.currentUser?.name;
        this.user.phone = this.loginComponent.currentUser?.phone;
        this.user.email = this.loginComponent.currentUser?.email;
        this.user.address = this.loginComponent.currentUser?.address;*/
        this.user = this.userService.loggedInUser;
    }

}