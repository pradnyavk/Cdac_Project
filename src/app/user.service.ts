import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IStudent } from './model/student';
import { IUser } from './model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url1 = "http://localhost:8080/user/list";
  private url2 = "http://localhost:8080/user/";
  private url3 = "http://localhost:8080/user/veryfication";

  constructor(private http: HttpClient) { }
  getUserDetail():Observable<IUser>{
    return this.http.get<IUser>(this.url1);
  }
  getUserById(id:String){
    var temp_url2 = this.url2+""+id;
    return this.http.get<IUser>(temp_url2);
  }
  saveUser(user:IUser){
    return this.http.post<IUser>(this.url2, user);
  }
  verfyUser(user:IUser){
    return this.http.post<IUser>(this.url3, user);
  }
  addStudent(id:String, studentData:IStudent){
    var url = this.url2+id+"/student";
    return this.http.put<IStudent[]>(url, studentData);
  }
}
