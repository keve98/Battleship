import { Component, Injectable, OnInit } from "@angular/core";
import { User } from "../user";
import { UserService } from "../user_service";
import { LoginUser } from "../login/login_user";


@Injectable({
    providedIn: 'root'
})
@Component({ templateUrl: 'player.component.html' })
export class PlayerComponent implements OnInit{

   public user : LoginUser = new LoginUser;
   public userData: User = new User;
   public username:string = "";

    constructor(private userService: UserService){
    }


   ngOnInit(){
       console.log("personal data init");
        this.username = sessionStorage.getItem('loggedUser')!;
        console.log("personal data username: " + this.username);
        (this.userService.getUserByUsername(this.username)).subscribe(
            (user)=>{
                this.userData.name = user.name;
                this.userData.phone = user.phone;
                this.userData.email = user.email;
                this.userData.address = user.address;
            }
        )
    }

}