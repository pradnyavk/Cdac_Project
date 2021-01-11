import { Component, OnInit } from '@angular/core';
import { StudentTeacherService } from '../student-teacher.service';

@Component({
  selector: 'app-hire-request-list',
  templateUrl: './hire-request-list.component.html',
  styleUrls: ['./hire-request-list.component.css']
})
export class HireRequestListComponent implements OnInit {

  studentTeacher:any;
  constructor(
    private _service: StudentTeacherService,
  ) { }
  ngOnInit(): void {
    this._service.getAllNewStudentTeacher().subscribe(data=>{this.studentTeacher = data
      console.log(data)
    });
  }

  conformStatus(id:String){
    this._service.confirmStatus(id)
    .subscribe(data=>this.studentTeacher = data);
  }

}
