import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { IStudent } from '../model/student';
import { IUser } from '../model/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-addStudent',
  templateUrl: './addStudent.component.html',
  styleUrls: ['./addStudent.component.css']
})
export class AddStudentComponent implements OnInit {
  id:String="";
  students:any;
  user:any;
  constructor(
    private route: ActivatedRoute,
    private _service:UserService,
    private router:Router
    ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params=>this.user = params);
   // console.log(this.user.id);
  }

  onSubmit(studentData:NgForm){
    var student= studentData.value;
    console.log(student);
     this._service.addStudent(this.user.id, student)
         .subscribe((data)=>{
           console.log(data);
          //  this.user.students = data;
           this.router.navigate(['user'],{queryParams:this.user});
         }); 
  }
}
