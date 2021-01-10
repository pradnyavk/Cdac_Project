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

@NgModule({
  declarations: [
    AppComponent,
    RoutingComponent,
    NavBarComponent,
    HiredTeacherListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [StudentServiceService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
