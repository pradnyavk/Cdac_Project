import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { IUser } from '../model/user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user:any;
  pp:any;
  showUserForm:boolean = true;
  showTeacherForm:boolean = false;
  constructor(
    private _userService: UserService,
    private rouer: Router
    ) {

      console.log("inside usr registration");
     }

  ngOnInit(): void {
  }

  onSelect(event:any){
    this.pp = event.target.files[0];
  }

  collectUserData(formvalue:NgForm){
    var user = formvalue.value;
    console.log(user);
    if(user.profile.match("TEACHER")){
      this.user = user;
      this.user.pp= undefined;
      this.showUserForm = false;
      this.showTeacherForm = true;
    }
    else{
     user.role ="USER";
     this.user = user;
     this.user.pp = undefined;
    this._userService.saveUser(this.user, this.pp)
    .subscribe(data=>console.log(data));
    this.rouer.navigate(["login"]);
    }
  }
}
