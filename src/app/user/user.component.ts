import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute,Router} from '@angular/router';
import { IUser } from '../model/user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user:any;
  imageData:any;
  showStudentSection:boolean = false;
  showTeacherSection:boolean = false;
  constructor(
    private route: ActivatedRoute,
    private router:Router         
    ) { console.log("user component")}
  ngOnInit(): void {
    this.route.queryParams.subscribe((params)=>{this.user = params;
      this.imageData = `data:${this.user.ppType};base64,${this.user.pp}`;
    });
  }
  addStudent(){
    this.router.navigate(['addStudent'], {relativeTo:this.route, queryParams:this.user, replaceUrl:true});
  }
  addTeacher(){
    this.router.navigate(['addTeacher'],{relativeTo:this.route, queryParams:this.user, replaceUrl:true});
  }
  getStudentList(){
    this.router.navigate(['user/studentList'], {queryParams:this.user, replaceUrl: true})
  }
  getTeacherList(){
    this.router.navigate(['user/teacherList'], {queryParams:this.user, replaceUrl: true})
  }

  toggleSS(){
    if(this.showStudentSection === false){
      this.showStudentSection = true;
      this.showTeacherSection = false;
    }
    else{
      this.showStudentSection = false;
    }
  }

  toggleTS(){
    if(this.showTeacherSection === false){
      this.showTeacherSection = true;
      this.showStudentSection = false;
    }
    else{
      this.showTeacherSection = false;
    }
  }

}
