import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { IUser } from '../model/user';
import { UserService } from '../user.service';
import{ Router} from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user:any;
  constructor(
    private _userService: UserService,
    private router: Router
    )
   { }

  ngOnInit(): void {
  }
  onSubmit(formData:NgForm){
    var user:IUser = formData.value;
    this._userService.verfyUser(user)
        .subscribe((data)=>{
          this.user=<IUser>data
          if(this.user != null){
            console.log(this.user);
            if(this.user.role.match("ADMIN")){
              this.router.navigate(["admin"], {queryParams: this.user});
            }
            else{
              console.log(this.user)
              console.log(this.user.students)
              this.router.navigate(["user"], {queryParams: this.user})
            }
          }
        });
  
  }
}
