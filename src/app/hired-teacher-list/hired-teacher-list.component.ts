import { Component, Input, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import { ITeacher } from '../model/teacher';
import { StudentServiceService } from '../student-service.service';
import { TeacherService } from '../teacher.service';

@Component({
  selector: 'app-hired-teacher-list',
  templateUrl: './hired-teacher-list.component.html',
  styleUrls: ['./hired-teacher-list.component.css']
})
export class HiredTeacherListComponent implements OnInit {

  @Input() studentId:any;
  teachers:ITeacher[] = [];
  teacherCourse:any;
  constructor(
    private _service: TeacherService,
   
  ) {  
  }

  ngOnInit(): void {
      this._service.getTeachersWhereStudentId(this.studentId)
      .subscribe(data => this.teachers = data);
  }

  getfrestList(){
    this._service.getTeachersWhereStudentId(this.studentId)
    .subscribe(data => this.teachers = data);
  }

  removeTeacher(teacherId:String){
    console.log("inside")
    this._service.removeTeacherWhereTeacherId(teacherId, this.studentId)
         .subscribe(data=>console.log(data));
  }

  
}
