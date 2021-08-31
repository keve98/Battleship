import { OnInit } from "@angular/core";
import { UserService } from "../user_service";

export class PlayerComponent implements OnInit{


    constructor(private userService: UserService){}


    ngOnInit(): void {
        this.getData();
    }

    getData(): void{}

}