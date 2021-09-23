import { Component, OnInit } from "@angular/core";
import { UserService } from "../user_service";
import { ActivatedRoute, Router } from '@angular/router';
import  AuthService  from './auth.service';
import { LoginUser } from "./login_user";


@Component({ templateUrl: 'login.component.html' })
export class LoginComponent{

    username: string;
    password : string;
    user = new LoginUser();
    message: any;
    errorMessage = 'Invalid Credentials';
    successMessage: string;
    invalidLogin = false;
    loginSuccess = false;

    constructor(private userService : UserService, private router:Router, private route: ActivatedRoute, private authenticationService: AuthService){
        this.username = "";
        this.password = "";
        this.successMessage = "";
    }


    doLogin() : void{
        this.username = (<HTMLInputElement>document.getElementById('uname')).value;
        this.password = (<HTMLInputElement>document.getElementById('psw')).value;
        console.log(this.username);

        this.user.username = this.username;
        this.user.password = this.password;

        this.userService.login(this.user).subscribe(
            (response: boolean)=>{
                console.log("Authenticated successfully");
            }
        )
             
        
    }
}
