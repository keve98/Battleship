import {User} from './user';
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import {BehaviorSubject, Observable, throwError} from 'rxjs';
import {HttpClient, HttpHeaderResponse, HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { UserData } from './user_data';
import { LoginUser } from './login/login_user';

@Injectable({
    providedIn: 'root'
})

export class UserService{
    private apiServerUrl=environment.apiBaseUrl;
    public user: Observable<User>;
    private userSubject: BehaviorSubject<User>;

    constructor(private http: HttpClient, private router: Router){
        this.userSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('user') || '{}'));
        this.user = this.userSubject.asObservable();
    }


    public login(user: LoginUser):Observable<any>{
       return this.http.post<any>(`${this.apiServerUrl}/login`, user);     
    }


    savaUserData(userData: UserData): Observable<any> {
        return this.http.post<any>(`${this.apiServerUrl}/save`, userData);
      }
    

    public getAllUsers():Observable<User[]>{
        return this.http.get<User[]>(`${this.apiServerUrl}/admin`);
    }


    public getUserByUsername(username: string): Observable<User>{
        return this.http.get<User>(`${this.apiServerUrl}/user/${username}`);
    }
}