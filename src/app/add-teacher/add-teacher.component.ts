import { Component, Input, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CourseService } from '../course.service';
import { ICourse } from '../model/course';
import { ITeacher, Teacher } from '../model/teacher';
import { IUser } from '../model/user';
import { TeacherService } from '../teacher.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-add-teacher',
  templateUrl: './add-teacher.component.html',
  styleUrls: ['./add-teacher.component.css']
})
export class AddTeacherComponent implements OnInit {
  @Input() user: any;
  @Input() pp:any;
  courses: any;
  teacher: any;
  selectedFiles: any;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private _service: TeacherService,
    private courseService: CourseService,
    private _userService: UserService
  ) { }
  ngOnInit(): void {
    this.courseService.getCourseList().subscribe(data => this.courses = data);
  }

  onFileChanged(event: any) {
    console.log(event);
    this.selectedFiles = event.target.files[0];
  }
  onSubmit(teacherData: NgForm) {
    let teacher1 = teacherData.value;
    console.log("courseId: "+teacher1.courseId);
    let phone = [];
    phone.push(teacher1.phone);
    let course: any;
    console.log(this.courses);
    console.log(teacher1);
    this.courses.forEach((element: ICourse) => {
       console.log(element.id);
      if (element.id == teacher1.courseId) {
        console.log("ele :"+element);
        course = element;
      }
    })
    console.log("after loop");
    this.user.role = "USER";
    console.log(course);
    let teacher: ITeacher = new Teacher(
      this.user.name,
      this.user.email,
      teacher1.age,
      this.user.gender,
      teacher1.expYear,
      { state: teacher1.state, city: teacher1.city },
      phone
    );
    this._userService.saveUser(this.user,this.pp).subscribe(data=> {
      this.user = JSON.parse(data);
      this._service.saveTeacher(this.selectedFiles, teacher, this.user.id)
        .subscribe(data1 => {
          let teacher = JSON.parse(data1);
          console.log(teacher.id);
          this._service.addCourseToTeacher(teacher.id, course)
            .subscribe(res => {
              this.router.navigate(["login"]);
            })
        });
    })

  }
}
