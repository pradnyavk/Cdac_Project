import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import {ActivatedRoute,Router} from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-add-teacher',
  templateUrl: './add-teacher.component.html',
  styleUrls: ['./add-teacher.component.css']
})
export class AddTeacherComponent implements OnInit {

  id:String="";
  user:any;
  teacher:any;
  constructor( 
    private route:ActivatedRoute,
    private _service:UserService,
    private router:Router
    ) 
    {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params=>this.user = params)
  }


  onSubmit(teacherData:NgForm)
  {
    this.teacher = teacherData.value;
    this._service.addTeacher(this.user.id,this.teacher)
    .subscribe((data)=>{
      console.log(data);
      this.user.teachers = data;
      this.router.navigate(['user'],{queryParams:this.user});
    });
  }
}
