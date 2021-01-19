import { Component, OnInit } from '@angular/core';
import { Subscriber } from 'rxjs';
import { AdminService } from '../admin.service';
import { TeacherService } from '../teacher.service';

@Component({
  selector: 'app-job-applications',
  templateUrl: './job-applications.component.html',
  styleUrls: ['./job-applications.component.css']
})
export class JobApplicationsComponent implements OnInit {
  teachers:any;
  teacherCourse:any;
  constructor(
    private _service : TeacherService,
    private adminService: AdminService
  ) { }

  ngOnInit(): void {
    this._service.getTeacherListWithStatusIsFalse()
        .subscribe(data=>this.teachers=data);
        this.adminService.getTeacherCourseWhereStatusIsFalse()
        .subscribe(data=>{
            this.teacherCourse = data;
            console.log(this.teacherCourse);
        })
  }

  confirmStatus(id:any){
    console.log(id);
    this._service.confirmTeacherStatus(id).subscribe(data=>{this.teachers = data});
  }
  reject(id:any){
    this._service.removeTeacherById(id).subscribe(data=>this.teachers = data);
  }

  confirmTeacherCourseStatus(id:any){
    this.adminService.confirmTeacherCourseStatus(id).subscribe(data=>{
      console.log("confirmed"+data)
    })
  }
  rejectTeacherCourse(id:any){
    this.adminService.rejectTeacherCourse(id).subscribe(data=>{
      console.log("confirmed"+data);
    })
  }

}
