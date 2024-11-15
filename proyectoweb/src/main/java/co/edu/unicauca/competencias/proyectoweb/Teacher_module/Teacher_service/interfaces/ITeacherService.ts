import { Teacher } from "../../Teacher_core/entities/Teacher";

export interface ITeacherService {
  getAllTeachers(): Promise<Teacher[]>;
  getTeacherById(id: number): Promise<Teacher | null>;
  createTeacher(Teacher: Teacher): Promise<Teacher>;
  updateTeacher(id: number, Teacher: Teacher): Promise<Teacher | null>;
  deleteTeacher(id: number): Promise<boolean>;
}
