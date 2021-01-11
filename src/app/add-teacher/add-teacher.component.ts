import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from '../course.service';
import { TeacherService } from '../teacher.service';

@Component({
  selector: 'app-add-teacher',
  templateUrl: './add-teacher.component.html',
  styleUrls: ['./add-teacher.component.css']
})
export class AddTeacherComponent implements OnInit {
  user:any;
  courses:any;

  constructor(
    private route: ActivatedRoute,
    private router:Router,
    private _service: TeacherService,
    private courseService: CourseService
  ) { }
  ngOnInit(): void {
    this.route.queryParams.subscribe(data=>this.user = data);
    this.courseService.getCourseList().subscribe(data=>this.courses = data);
  }

  onSubmit(teacherData:NgForm){
    console.log("inside");
    console.log(teacherData.value);
    let teacher = {
      teacherName:teacherData.value.teacherName
    }
    this._service.saveTeacher(teacher,this.user.id)
    .subscribe(data=>console.log(data));
  }


}
