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
    ) { console.log("user component")}
  ngOnInit(): void {
    this.route.queryParams.subscribe((params)=>this.user = params);
    console.log(this.user.name);
  }
  addStudent(){
    this.router.navigate(['addStudent'], {relativeTo:this.route, queryParams:this.user, replaceUrl:true});
  }

  getStudentList(){
    console.log("inside student list");
    console.log(this.user.name);
    this.router.navigate(['user/studentList'], {queryParams:this.user, replaceUrl: true})
  }

}
