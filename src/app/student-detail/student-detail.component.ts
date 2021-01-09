import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IAddress } from '../model/address';
import { IStudent, Student } from '../model/student';
import { ITeacher } from '../model/teacher';
import { StudentServiceService } from '../student-service.service';
import { TeacherService } from '../teacher.service';

@Component({
  selector: 'app-student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./student-detail.component.css']
})
export class StudentDetailComponent implements OnInit {
  student:IStudent;
  teachers:ITeacher[]=[];
  constructor(
    private router: Router,
    private route:ActivatedRoute,
    private _service: StudentServiceService,
    private _teacherService: TeacherService
  ) { 

    this.student= <IStudent>{};
    this.student.address = <IAddress>{state:"maha", city:"pune"};
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(param=>this.student = <IStudent>param)
  }

  getTeachers(formref:NgForm){
    var subject = formref.value.subject;
    this._teacherService.getTeachersBySubjectAndAdd(subject, this.student.address.state, this.student.address.city)
          .subscribe(data=>this.teachers = data);
  }

}
