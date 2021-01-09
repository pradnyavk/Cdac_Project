import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ITeacher } from './model/teacher';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {
  baseUrl:String="http://localhost:8080/teacher/list/detail?";
  constructor(
    private _http: HttpClient
  ) { }

  getTeachersBySubjectAndAdd(subject:String, state:String, city:String):Observable<ITeacher[]>{
    let url = this.baseUrl+"subject="+subject+"&state="+state+"&city="+city; 
    return this._http.get<ITeacher[]>(url);
  }
}
