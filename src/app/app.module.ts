import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule, RoutingComponent } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentServiceService } from './student-service.service';
import {HttpClientModule} from '@angular/common/http';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { FormsModule } from '@angular/forms';
import { UserService } from './user.service';
import { HiredTeacherListComponent } from './hired-teacher-list/hired-teacher-list.component';
import { HireRequestListComponent } from './hire-request-list/hire-request-list.component';
import { AddTeacherComponent } from './add-teacher/add-teacher.component';
import { TeacherListComponent } from './teacher-list/teacher-list.component';
import { TeacherDetailComponent } from './teacher-detail/teacher-detail.component';
import { JobApplicationsComponent } from './job-applications/job-applications.component';
import { AuthGuard } from './auth.guard';

@NgModule({
  declarations: [
    AppComponent,
    RoutingComponent,
    NavBarComponent,
    HiredTeacherListComponent,
    HireRequestListComponent,
    AddTeacherComponent,
    TeacherListComponent,
    TeacherDetailComponent,
    JobApplicationsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [StudentServiceService, AuthGuard, UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
