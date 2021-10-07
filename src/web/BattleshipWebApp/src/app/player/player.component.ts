import { Component, Injectable, OnInit } from "@angular/core";
import { User } from "../user";
import { UserService } from "../user_service";
import { LoginComponent } from "../login";
import { LoginUser } from "../login/login_user";


@Injectable({
    providedIn: 'root'
})
@Component({ templateUrl: 'player.component.html' })
export class PlayerComponent implements OnInit{

   public user : LoginUser = new LoginUser;
   public userData: User | undefined;
   username: string = "";

    constructor(private userService: UserService, private loginComponent: LoginComponent){
    }


    ngOnInit(): void {
        this.user = this.userService.loggedInUser;
        
        this.userService.getUserByUsername(this.user.username!).subscribe(
            (user)=>{
                this.userData = user;
            }
        )
    }

}