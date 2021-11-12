import { ChangeDetectorRef, Component, Injectable, OnInit } from "@angular/core";
import { UserService } from "../user_service";
import { Router } from '@angular/router';
import { LoginUser } from "./login_user";
import { User } from "../user";
import { RouterModule, Routes } from '@angular/router';
import { UserRole } from "./userRole";
import { HttpErrorResponse } from "@angular/common/http";




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
        this.userService.login(this.user)
            .subscribe(
                (user: UserRole) => {
                    if (user.principle === "ADMIN") {
                        this.reloadPage("/adminwelcome");
                        this.isAdmin = true;
                    } else {
                        this.isAdmin = false;
                        this.reloadPage("/welcome");
                    }
                    console.log(user.principle);
                    sessionStorage.setItem('loggedUser', this.username);
                    this.isAuthenticated = true;
                },
                (error) =>{
                    alert(JSON.stringify(error));
                    this.reloadPage("/login");
                }
            );
      
    }

    reloadPage(url: String){
        this.router.navigate([`${url}`])
            .then(() => {
    window.location.reload();
  });
    }


   async doFunction() {
       await this.doLogin();
       this.router.navigate([`/`]);
    }

}

