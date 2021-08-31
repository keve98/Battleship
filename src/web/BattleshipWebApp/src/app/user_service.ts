import {User} from './user';
import { Injectable } from "@angular/core";
import { environment } from "src/environments/environment";
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaderResponse, HttpHeaders} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})

export class UserService{
    private apiServerUrl=environment.apiBaseUrl;

    constructor(private http: HttpClient){}


    public login(username: string, password: string){
        const headers = new HttpHeaders({Authorization: 'Basic ' + btoa(username + ":" + password)})
        return this.http.get(`${this.apiServerUrl}/login`,{headers,responseType: 'text' as 'json'})
    }


    public getAllUsers():Observable<User[]>{
        return this.http.get<User[]>(`${this.apiServerUrl}/admin`);
    }


    public getUserByUsername(username: string): Observable<User>{
        return this.http.get<User>(`${this.apiServerUrl}/user/${username}`);
    }
}