import {IStudent} from "./student"
import { ITeacher } from "./teacher";
export interface IUser{
    id:number,
    name: String,
    email:String,
    password:String,
    gender: String,
    role: String,
    students:IStudent[],
    teachers:ITeacher[]
}