import {User} from './user';
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import {BehaviorSubject, Observable, throwError} from 'rxjs';
import {HttpClient, HttpHeaderResponse, HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';

import { LoginUser } from './login/login_user';
import { PlayerwelcomeComponent } from './playerwelcome';

@Injectable({
    providedIn: 'root'
})
export class UserService{
    private apiServerUrl=environment.apiBaseUrl;
    public user: Observable<User>;
    private userSubject: BehaviorSubject<User>;
    public loggedInUser: LoginUser = new LoginUser;


    constructor(private http: HttpClient, private router: Router){
        this.userSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('user') || '{}'));
        this.user = this.userSubject.asObservable();
        console.log("userservice const");
    }

    public fillLoggedInUser(user: LoginUser): void{
            this.loggedInUser = user;
            console.log("Service logged in user: " + this.loggedInUser.username);
    }

    public getLoggedInUser() : LoginUser{
        return this.loggedInUser
    }

    async login(user: LoginUser):Promise<Observable<boolean>>{
        const t = await this.http.post<boolean>(`${this.apiServerUrl}/login`, user)
        return t;     
     }


    public savaUserData(user: User): Observable<any> {
        return this.http.post<any>(`${this.apiServerUrl}/save`, user);
      }
    

    public getAllUsers():Observable<User[]>{
        return this.http.get<User[]>(`${this.apiServerUrl}/admin`);
    }


    public getUserByUsername(username: string): Observable<User>{
        const t = this.http.get<User>(`${this.apiServerUrl}/user/${username}`)
        return t;
    }

    public logout(){
        this.http.get(`${this.apiServerUrl}/logout`);
    }

    async  isAdminOrUser():Promise<Observable<boolean>>{
        const t = this.http.get<boolean>(`${this.apiServerUrl}/isAdmin`)
        return t;
    }
}