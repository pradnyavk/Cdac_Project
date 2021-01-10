import { Address } from "./address";

export interface IStudent{
    id:String,
    studentName:String,
    dob:Date,
    email:String,
    gender:String,
    address:any,
    city:String,
    state:String,
    phone:[],
    courses:[],
    teachers:[],
    userName:{}
}

// export class Student implements IStudent {
//     id:String;
//     studentName:String;
//     dob:Date;
//     email:String;
//     gender:String;
//     address:Address;
//     phone:[];
//     courses:[];
//     teachers:[];
//     userName:{}
//     constructor(
//         id:String,
//         studentName:String,
//         dob:Date,
//         email:String,
//         gender:String,
//         address:Address,
//         phone:[],
//         courses:[],
//         teachers:[],
//         userName:{}
//     ){
//         this.id = id;
//         this.studentName = studentName;
//         this.dob = dob;
//         this.email = email;
//         this.gender = gender;
//         this.phone = phone;
//         this.gender = gender;
//         this.teachers = teachers;
//         this.userName = userName;
//         this.address = address;
//         this.courses = courses;
//     }
// }