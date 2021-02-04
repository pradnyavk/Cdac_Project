import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from '../model/address';
import { IStudent } from '../model/student';
import { ITeacher } from '../model/teacher';
import { StudentServiceService } from '../student-service.service';
import { StudentTeacherService } from '../student-teacher.service';
import { TeacherService } from '../teacher.service';

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.css']
})
export class StudentDetailComponent implements OnInit {
  student:IStudent=<IStudent>{};
  teachers:ITeacher[]=[];
  searcheSubject:String= new String();
  constructor(
    private router: Router,
    private route:ActivatedRoute,
    private _service: StudentServiceService,
    private sttservice:StudentTeacherService,
    private _teacherService: TeacherService
  ) { 
    console.log("inside studentDetail constr")
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(param=>this.student =<IStudent>param)
  }

  getTeachers(formref:NgForm){
    var subject = formref.value.subject;
    this.searcheSubject = subject;
    console.log(this.student.address);
    this._teacherService.getTeachersBySubjectAndAdd(subject, "maha","pune")
          .subscribe(data=>this.teachers = data);
  }

  addTeacher(teacherId:String){
   this.sttservice.addTeacher(teacherId, this.student.id, this.searcheSubject)
  }

}
