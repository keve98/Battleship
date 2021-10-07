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
    tmp: boolean = false;

    public currentUser: User = new User;

    constructor(private userService : UserService, private router:Router, private route: RouterModule, private authenticationService: AuthService){
        this.username = "";
        this.password = "";
        console.log("logincomponent const");
    }


    async doLogin(){
        this.username = (<HTMLInputElement>document.getElementById('uname')).value;
        this.password = (<HTMLInputElement>document.getElementById('psw')).value;

        this.user.username = this.username;
        this.user.password = this.password;
        this.userService.login(this.user)
        .subscribe(
           (isValid) => {
               console.log("login started");
               if(isValid){
                sessionStorage.setItem('token', btoa(this.username + ':' + this.password));
                console.log("login valid");
                console.log(this.username);
                this.userService.fillLoggedInUser(this.user);
                this.tmp = true;
               }
                else{
                    alert("bad credentials");
                    console.log("login invalid");
                    this.username = "";
                    this.password = "";
                    this.user.username = this.username;
                    this.user.password = this.password;
                }
           }
        ); 

        
       }


       async doFunction(){
            await this.doLogin();
            await this.router.navigate([`/welcome`]);
       }

    }

