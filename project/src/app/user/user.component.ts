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
  constructor(
    private route: ActivatedRoute,
    private router:Router         
    ) { }
  ngOnInit(): void {
    this.route.queryParams.subscribe((params)=>this.user = params);
    console.log(this.user.name);
    console.log(this.user.students);
  }
  addStudent(){
    this.router.navigate(['addStudent'], {relativeTo:this.route, queryParams:this.user, replaceUrl:true});
  }

  getStudentList(){
    console.log("urk")
    this.router.navigate(['user/studentList'], {queryParams:this.user, replaceUrl: true})
  }

}
