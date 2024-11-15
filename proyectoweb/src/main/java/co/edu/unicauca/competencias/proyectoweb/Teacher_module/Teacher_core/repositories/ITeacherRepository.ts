import { Teacher } from "../entities/Teacher";

export interface ITeacherRepository {
  findAll(): Promise<Teacher[]>;
  findById(id: number): Promise<Teacher | null>;
  create(Teacher: Teacher): Promise<Teacher>;
  update(id: number, Teacher: Teacher): Promise<Teacher | null>;
  delete(id: number): Promise<boolean>;
}
