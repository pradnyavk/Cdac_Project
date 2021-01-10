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
  constructor(
    private _userService: UserService,
    private rouer: Router
    ) {

      console.log("inside usr registration");
     }

  ngOnInit(): void {
  }

  collectData(formvalue:NgForm){
    var user:IUser = formvalue.value;
    console.log(user);
    this._userService.saveUser(user)
    .subscribe(data=>console.log(data));
    this.rouer.navigate(["login"]);
  }
}
