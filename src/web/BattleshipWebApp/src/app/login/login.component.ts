import { Component, Injectable, OnInit } from "@angular/core";
import { UserService } from "../user_service";
import { Router } from '@angular/router';
import { LoginUser } from "./login_user";
import { User } from "../user";
import { RouterModule, Routes } from '@angular/router';
import { ThrowStmt } from "@angular/compiler";




@Injectable({
    providedIn: 'root'
})
@Component({ templateUrl: 'login.component.html' })
export class LoginComponent {

    username: string;
    password: string;


    user = new LoginUser();
    routing: string = "";
    
    isAuthenticated : boolean = true;
    isAdmin : boolean = false;

    public currentUser: User = new User;

    constructor(private userService: UserService, private router: Router, private route: RouterModule) {
        this.username = "";
        this.password = "";
    }


   async doLogin() {
        this.username = (<HTMLInputElement>document.getElementById('uname')).value;
        this.password = (<HTMLInputElement>document.getElementById('psw')).value;

        this.user.username = this.username;
        this.user.password = this.password;
        (await this.userService.login(this.user))
        .subscribe(
            async (isValid : boolean)=>{
                if(isValid){
                    sessionStorage.setItem('loggedUser', this.username);
                    this.isAuthenticated = isValid;
                    (await this.userService.isAdminOrUser()).subscribe(
                        (admin: boolean) =>{
                            this.isAdmin = admin;
                            if(this.isAdmin){
                                alert("Redirect to admin page...");
                                this.router.navigate([`/adminwelcome`])
                            }else{
                                this.router.navigate([`/welcome`]);
                            }
                    }
                    );
                }else{
                    alert("Authentication failed.")
                    this.isAuthenticated = isValid;
                    this.router.navigate([`/login`]);                }
            }
        );
            
    }

    async getUser(){
        (await this.userService.getUserByUsername(this.username)).subscribe(
            (user: User) => {
                this.currentUser = user;
            }
        )
    }


    async doFunction() {
        await this.doLogin();
          this.router.navigate([`/welcome`]);
    }

    public routeTo(url: string){
        this.router.navigate([url]);
    }
}

