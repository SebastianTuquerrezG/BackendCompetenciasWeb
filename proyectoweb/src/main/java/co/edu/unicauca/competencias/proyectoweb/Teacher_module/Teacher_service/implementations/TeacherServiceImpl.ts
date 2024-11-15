import { ITeacherService } from "../interfaces/ITeacherService";
import { ITeacherRepository } from "../../Teacher_core/repositories/ITeacherRepository";
import { Teacher } from "../../Teacher_core/entities/Teacher";

export class TeacherServiceImpl implements ITeacherService {
  private TeacherRepository: ITeacherRepository;

  constructor(TeacherRepository: ITeacherRepository) {
    this.TeacherRepository = TeacherRepository;
  }

  async getAllTeachers(): Promise<Teacher[]> {
    return await this.TeacherRepository.findAll();
  }

  async getTeacherById(id: number): Promise<Teacher | null> {
    return await this.TeacherRepository.findById(id);
  }

  async createTeacher(Teacher: Teacher): Promise<Teacher> {
    return await this.TeacherRepository.create(Teacher);
  }

  async updateTeacher(id: number, Teacher: Teacher): Promise<Teacher | null> {
    return await this.TeacherRepository.update(id, Teacher);
  }

  async deleteTeacher(id: number): Promise<boolean> {
    return await this.TeacherRepository.delete(id);
  }
}
