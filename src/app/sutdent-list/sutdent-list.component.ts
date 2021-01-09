import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IStudent, Student } from '../model/student';
import { IUser } from '../model/user';
import { StudentServiceService } from '../student-service.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-sutdent-list',
  templateUrl: './sutdent-list.component.html',
  styleUrls: ['./sutdent-list.component.css']
})
export class SutdentListComponent implements OnInit {
  user:any;
  students:IStudent[]= [];
  constructor(
    private route : ActivatedRoute,
    private router: Router,
    private _service: StudentServiceService
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params=>this.user = params);
    this._service.getStudentByUserId(this.user.id)
        .subscribe(data=>this.students = data);
  }
  getStudentDetail(id:String){
    let student:IStudent;
    this.students.forEach(data=>{
      if(data.id == id){
        student = data;
        this.router.navigate(['user/studentDetail'],{queryParams:student, replaceUrl:true});
      }
    })
   
  }

}
