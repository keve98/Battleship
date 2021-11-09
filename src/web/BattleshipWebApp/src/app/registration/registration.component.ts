import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { User } from "../user";
import { UserService } from "../user_service";

@Component({ templateUrl: 'registration.component.html'})
export class RegistrationComponent{
    

    public user= new User();

    constructor(private userService: UserService, private router : Router){}

    public save(): void{
        this.user.username = (<HTMLInputElement>document.getElementById('username')).value;
        this.user.name = (<HTMLInputElement>document.getElementById('name')).value;
        this.user.password = (<HTMLInputElement>document.getElementById('psw')).value;
        this.user.address = (<HTMLInputElement>document.getElementById('address')).value;
        this.user.phone = (<HTMLInputElement>document.getElementById('phone')).value;
        this.user.email = (<HTMLInputElement>document.getElementById('email')).value;

        this.userService.savaUserData(this.user).subscribe(
            (data : User) => {
                alert("Registration successful for user: " + this.user.username);
                this.router.navigate([`/login`]);
            },
            (error) => {
                alert("Something went wrong, try again.");
                this.router.navigate([`/registration`]);
            }
        );

    }
}