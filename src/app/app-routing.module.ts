import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { AddStudentComponent } from './addStudent/addStudent.component';
import { UserComponent } from './user/user.component';
import { SutdentListComponent} from './sutdent-list/sutdent-list.component'
import { StudentDetailComponent } from './student-detail/student-detail.component';

const routes: Routes = [
  {path:'', redirectTo:'dashboard', pathMatch:'full'},
  {path:'admin', component:AdminComponent},
  {path:'user', component:UserComponent, 
    children:[
      {path:'addStudent', component: AddStudentComponent},
      {path: 'studentList', component:SutdentListComponent},
      {path:"studentDetail", component:StudentDetailComponent} 
    ]},
  {path:'dashboard', component:DashboardComponent},
  {path:'login', component:LoginComponent},
  {path:'register', component:RegistrationComponent}
  //{path:'**', component:}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const RoutingComponent=[DashboardComponent, LoginComponent, RegistrationComponent, AdminComponent, UserComponent, AddStudentComponent,SutdentListComponent, StudentDetailComponent]
