import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ITeacher } from './model/teacher';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {
 
  baseUrl:String="http://localhost:8080/teacherCourse/teacher?";
  constructor(
    private _http: HttpClient
  ) { }

  getTeachersBySubjectAndAdd(subject:String, state:String, city:String):Observable<ITeacher[]>{
    let url = this.baseUrl+"courseName="+subject+"&state="+state+"&city="+city; 
    return this._http.get<ITeacher[]>(url);
  }
  getTeachersWhereStudentId(id:String):Observable<ITeacher[]>{
    let url="http://localhost:8080/studentTeacher/student/"+id;
    return this._http.get<ITeacher[]>(url);
  }
  removeTeacherWhereTeacherId(teacherId: String, studentId: String) {
    let url = "http://localhost:8080/studentTeacher/remove/teacher/"+teacherId+"/"+studentId;
    return this._http.get<String>(url);
  }
}
