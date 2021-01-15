import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { AddStudentComponent } from './addStudent/addStudent.component';
import { UserComponent } from './user/user.component';
import { SutdentListComponent} from './sutdent-list/sutdent-list.component'
import { StudentDetailComponent } from './student-detail/student-detail.component';
import { AddTeacherComponent } from './add-teacher/add-teacher.component';
import { TeacherListComponent } from './teacher-list/teacher-list.component';
import { compileComponentFromMetadata } from '@angular/compiler';
import { TeacherDetailComponent } from './teacher-detail/teacher-detail.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  {path:'', redirectTo:'dashboard', pathMatch:'full'},
  {path:'admin', component:AdminComponent, canActivate:[AuthGuard]},
  {path:'user', component:UserComponent, 
    children:[
      {path:'addStudent', component: AddStudentComponent},
      {path: 'studentList', component:SutdentListComponent},
      {path:"studentDetail", component:StudentDetailComponent},
      {path:'addTeacher', component: AddTeacherComponent},
      {path:'teacherList', component:TeacherListComponent}, 
    ], canActivate:[AuthGuard] },
  {path:'teacher', component:TeacherDetailComponent, canActivate:[AuthGuard]},
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
export const RoutingComponent=[DashboardComponent, LoginComponent, RegistrationComponent, AdminComponent, UserComponent, AddStudentComponent,SutdentListComponent, StudentDetailComponent, TeacherListComponent, TeacherDetailComponent, AddTeacherComponent]
