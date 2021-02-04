import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IStudent } from '../model/student';
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
  ) { 
    console.log("inside student list const")
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params=>this.user = params);
    this._service.getStudentByUserId(this.user.id)
        .subscribe(data=>{this.students = data;
          console.log(this.students[0].address.city); 
        });  
  }
  getStudentDetail(id:String){
    let student:IStudent;
    this.students.forEach(data=>{
      if(data.id == id){
        student = data;
        console.log("from student list:"+data.address.city);
        console.log("from student list:"+student.address.city);       
        this.router.navigate(['user/studentDetail'],{queryParams:student, replaceUrl:true});
      }
    })
   
  }

}
