import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TeacherService } from '../teacher.service';

@Component({
  selector: 'app-teacher-detail',
  templateUrl: './teacher-detail.component.html',
  styleUrls: ['./teacher-detail.component.css']
})
export class TeacherDetailComponent implements OnInit {
  id:any;
  teacher:any;
  constructor(
    private route:ActivatedRoute,
    private router: Router,
    private _service: TeacherService
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(data=>{
      this.id = data.id;
      this._service.findTeacherById(this.id)
      .subscribe(data =>this.teacher = data);
    });
  }

}
