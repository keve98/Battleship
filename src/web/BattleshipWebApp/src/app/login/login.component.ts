import { Component, OnInit } from "@angular/core";
import { UserService } from "../user_service";
import { ActivatedRoute, Router } from '@angular/router';
import  AuthService  from './auth.service';

@Component({ templateUrl: 'login.component.html' })
export class LoginComponent{

    username: string;
    password : string;
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

        let resp = this.userService.login(this.username, this.password);
        resp.subscribe(data => {
          this.message = data;
         this.router.navigate(["/admin"])
        });
        
         /*   this.authenticationService.authenticationService(this.username, this.password).subscribe((result) => {
                this.invalidLogin = false;
                this.loginSuccess = true;
                this.successMessage = 'Login Successful.';
                this.router.navigate(['/']);
              }, () => {
                this.invalidLogin = true;
                this.loginSuccess = false;
            })
        }*/
        
    }
}
