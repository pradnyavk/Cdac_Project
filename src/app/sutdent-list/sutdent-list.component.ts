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
  students:any;
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
        });  
  }
  getStudentDetail(id:String){
    // let studentId;
    // for(let i=0; i<this.students.length; i++){
    //   if(this.students[i].id == id){
    //     studentId = this.students[i];
    //     console.log("from student list:"+this.students[i].location.city);
    //     console.log("id: "+)      
        this.router.navigate(['user/studentDetail'],{queryParams:{id:id}, replaceUrl:true});
    //   }
    // }

   
  }

}
