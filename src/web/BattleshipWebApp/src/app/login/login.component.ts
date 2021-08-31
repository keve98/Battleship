import { Component, OnInit } from "@angular/core";
import { UserService } from "../user_service";
import { Router } from '@angular/router';
import { User } from "../user";

@Component({ templateUrl: 'login.component.html' })
export class LoginComponent implements OnInit{

    user: User | undefined;

    constructor(private userService : UserService, private router:Router){}

    ngOnInit(): void {
        this.doLogin();
    }

    doLogin() : void{
        var uname = (<HTMLInputElement>document.getElementById('uname')).value;
        var passw = (<HTMLInputElement>document.getElementById('psw')).value;

        if(uname === '' || passw === ''){
            alert("Username and password required");
        }else{
            this.userService.login(uname, passw).subscribe(
                (response)=>{
                return this.router.navigateByUrl("admin");
                }
            );
        }
        
    }
    
}