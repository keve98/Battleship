import { Component } from "@angular/core";
import { UserData } from "../user_data";
import { UserService } from "../user_service";

@Component({ templateUrl: 'registration.component.html' })
export class RegistrationComponent{
    

    public user= new UserData();

    constructor(private userService: UserService){}

    public save(): void{
        this.user.username = (<HTMLInputElement>document.getElementById('username')).value;
        this.user.name = (<HTMLInputElement>document.getElementById('name')).value;
        this.user.password = (<HTMLInputElement>document.getElementById('psw')).value;
        this.user.address = (<HTMLInputElement>document.getElementById('address')).value;
        this.user.phone = (<HTMLInputElement>document.getElementById('phone')).value;
        this.user.email = (<HTMLInputElement>document.getElementById('email')).value;

        this.userService.savaUserData(this.user).subscribe(
            data => console.log("response received"),
            error => console.log("error occured")
        );

    }

}