import { Component, Injectable, OnInit } from "@angular/core";
import { UserService } from "../user_service";
import { ActivatedRoute, Router } from '@angular/router';
import { LoginUser } from "./login_user";
import { User } from "../user";
import { RouterModule, Routes } from '@angular/router';
import { waitForAsync } from "@angular/core/testing";
import { SelectMultipleControlValueAccessor } from "@angular/forms";



@Injectable({
    providedIn: 'root'
})
@Component({ templateUrl: 'login.component.html' })
export class LoginComponent {

    username: string;
    password: string;
    user = new LoginUser();

    public currentUser: User = new User;

    constructor(private userService: UserService, private router: Router, private route: RouterModule) {
        this.username = "";
        this.password = "";
        console.log("logincomponent const");
    }


   async doLogin() {
        this.username = (<HTMLInputElement>document.getElementById('uname')).value;
        this.password = (<HTMLInputElement>document.getElementById('psw')).value;

        this.user.username = this.username;
        this.user.password = this.password;
        await this.userService.login(this.user)
        .subscribe(
           (data: User) => {
                sessionStorage.setItem('token', btoa(this.username + ':' + this.password));
           }
        );         
    }


    getUser(): void{
        this.userService.getUserByUsername(this.username).subscribe(
            (user: User) => {
                this.currentUser = user;
            }
        )
    }


    async doFunction() {
        //this.getUser();
        this.doLogin();

        this.router.navigate([`/welcome`]);
        this.userService.fillLoggedInUser(this.currentUser);
    }

}

