import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentServiceService } from '../student-service.service';
import { TeacherService } from '../teacher.service';

@Component({
  selector: 'app-teacher-detail',
  templateUrl: './teacher-detail.component.html',
  styleUrls: ['./teacher-detail.component.css']
})
export class TeacherDetailComponent implements OnInit {
  user:any;
  teacher:any;
  imageData:any;
  constructor(
    private route:ActivatedRoute,
    private router: Router,
    private _teacherService: TeacherService,
    private _studentService: StudentServiceService,
    // private _sessionService: SessionService
  ) { }
  ngOnInit(): void {
    this.route.queryParams.subscribe(data=>{
      this.user = data;
      this._teacherService.getTeachersByUserId(this.user.id)
      .subscribe(data =>{this.teacher = data;
        this.imageData = `data:${this.user.ppType};base64,${this.user.pp}`;
      });
    });
  }

  registerForCourse(course:any){

  }
  seeJobs(){

  }
  getSessionsList(){
    this._teacherService.getAllSessions(this.teacher[0].id).subscribe(data=>{}, error=>{
      console.log("error message")
    });
  }
  removeStduent(studentId:any){
    this._teacherService.removeStudent(studentId, this.teacher[0].id).subscribe(data=>{}, error=>{
      console.log("error message")
    });
  }
  rejectJob(){

  }
  studentDetail(studentId:any){
   this._studentService.getStudentById(studentId).subscribe(data=>{}, error=>{
     console.log("error message")
   })
  }

  studentList(teacherId:any){
    this._teacherService.getStudentList(this.teacher[0].id).subscribe(data=>{}, error=>{
      console.log("error message")
    })
  }
  getNotice(){

  }
}
