import { Component, Injectable, OnInit } from "@angular/core";
import { UserService } from "../user_service";
import { ActivatedRoute, Router } from '@angular/router';
import AuthService from './auth.service';
import { LoginUser } from "./login_user";
import { User } from "../user";
import { RouterModule, Routes } from '@angular/router';
import { waitForAsync } from "@angular/core/testing";
import { SelectMultipleControlValueAccessor } from "@angular/forms";
import { map } from "rxjs/operators";



@Injectable({
    providedIn: 'root'
})
@Component({ templateUrl: 'login.component.html' })
export class LoginComponent {

    username: string;
    password: string;
    user = new LoginUser();

    public currentUser: User = new User;

    constructor(private userService: UserService, private router: Router, private route: RouterModule, private authenticationService: AuthService) {
        this.username = "";
        this.password = "";
        console.log("logincomponent const");
    }


    async doLogin() {
        this.username = (<HTMLInputElement>document.getElementById('uname')).value;
        this.password = (<HTMLInputElement>document.getElementById('psw')).value;

        this.user.username = this.username;
        this.user.password = this.password;
        this.userService.login(this.user)
            .subscribe(
                (isValid) => {
                    console.log("login started");
                    if (isValid) {
                        sessionStorage.setItem('token', btoa(this.username + ':' + this.password));
                        console.log("login valid");
                        console.log(this.username);
                        this.userService.fillLoggedInUser(this.user);
                        console.log(this.user.username);
                        
                    }
                    else {
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

    async login(): Promise<boolean> {
        this.username = (<HTMLInputElement>document.getElementById('uname')).value;
        this.password = (<HTMLInputElement>document.getElementById('psw')).value;

        this.user.username = this.username;
        this.user.password = this.password;


        const result = await this.userService.login(this.user).toPromise();
        console.log(result);
        return result;
        
    }


    async doFunction(): Promise<any> {

        await this.doLogin();
        this.router.navigate([`/welcome`]);

        /*const success = await this.login();
        if(success){
            this.router.navigate([`/welcome`]);
            return;
        }*/

        
    }

}

