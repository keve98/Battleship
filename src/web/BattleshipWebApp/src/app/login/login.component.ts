import { Component, Injectable, OnInit } from "@angular/core";
import { UserService } from "../user_service";
import { ActivatedRoute, Router } from '@angular/router';
import  AuthService  from './auth.service';
import { LoginUser } from "./login_user";
import { User } from "../user";
import { RouterModule, Routes } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
@Component({ templateUrl: 'login.component.html' })
export class LoginComponent{

    username: string;
    password : string;
    user = new LoginUser();

    public currentUser: User = new User;

    constructor(private userService : UserService, private router:Router, private route: RouterModule, private authenticationService: AuthService){
        this.username = "";
        this.password = "";
    }


    getUser(): void{
        this.userService.getUserByUsername(this.username).subscribe(
            (user: User) => {
                this.currentUser = user;
            }
        )
    }

    doLogin() : void{
        this.username = (<HTMLInputElement>document.getElementById('uname')).value;
        this.password = (<HTMLInputElement>document.getElementById('psw')).value;

        this.user.username = this.username;
        this.user.password = this.password;
        this.userService.login(this.user)
        .subscribe(
           (isValid: boolean) => {
               if(isValid){
                sessionStorage.setItem('token', btoa(this.username + ':' + this.password));
               }
               else{
                alert("Authentication failed.");
               }
           }
        );        
    }

    doFunction(): void{
        this.doLogin();
        this.getUser();
        this.userService.fillLoggedInUser(this.currentUser);
        this.router.navigate(['/player']);
    }


   
}
