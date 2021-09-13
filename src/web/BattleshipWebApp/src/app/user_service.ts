import {User} from './user';
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import {BehaviorSubject, Observable, throwError} from 'rxjs';
import {HttpClient, HttpHeaderResponse, HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';
import { map, catchError } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})

export class UserService{
    private apiServerUrl=environment.apiBaseUrl;
    public user: Observable<User>;
    private userSubject: BehaviorSubject<User>;
  //  private currentUserSubject: BehaviorSubject<User>;

    constructor(private http: HttpClient, private router: Router){
        this.userSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('user') || '{}'));
        this.user = this.userSubject.asObservable();
       // this.currentUserSubject = new BehaviorSubject<User>();
    }


    public login(username:string, password:string):Observable<void>{
       // const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
       // return this.http.get("http://localhost:8080/login",{headers,responseType: 'text' as 'json'})
       return this.http.post<void>(`${this.apiServerUrl}/login`, { username, password })
       .pipe(map(user => {
           // store user details and jwt token in local storage to keep user logged in between page refreshes
           localStorage.setItem('currentUser', JSON.stringify(user));
           //this.currentUserSubject.next(user);
           return user;
       }));      
    }


    public getAllUsers():Observable<User[]>{
        return this.http.get<User[]>(`${this.apiServerUrl}/admin`);
    }


    public getUserByUsername(username: string): Observable<User>{
        return this.http.get<User>(`${this.apiServerUrl}/user/${username}`);
    }
}