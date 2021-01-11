import { Component, OnInit } from '@angular/core';
import { Subscriber } from 'rxjs';
import { TeacherService } from '../teacher.service';

@Component({
  selector: 'app-job-applications',
  templateUrl: './job-applications.component.html',
  styleUrls: ['./job-applications.component.css']
})
export class JobApplicationsComponent implements OnInit {
  teachers:any;
  constructor(
    private _service : TeacherService
  ) { }

  ngOnInit(): void {
    this._service.getTeacherListWithStatusIsFalse()
        .subscribe(data=>this.teachers=data);
  }

  confirmStatus(id:any){
    this._service.confirmTeacherStatus(id);
    this._service.getTeacherListWithStatusIsFalse()
        .subscribe(data=>this.teachers=data);
  }
  reject(id:any){
    this._service.removeTeacherById(id);
    this._service.getTeacherListWithStatusIsFalse()
        .subscribe(data=>this.teachers=data);
  }

}
