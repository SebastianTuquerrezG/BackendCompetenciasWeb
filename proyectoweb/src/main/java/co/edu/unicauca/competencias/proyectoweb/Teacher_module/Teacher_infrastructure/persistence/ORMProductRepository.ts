import { ITeacherRepository } from "../../Teacher_core/repositories/ITeacherRepository";
import { Teacher } from "../../Teacher_core/entities/Teacher";
import { Repository } from "typeorm";
import { AppDataSource } from "./data-source";

export class TypeORMTeacherRepository implements ITeacherRepository {
  private repository: Repository<Teacher>;

  constructor() {
    this.repository = AppDataSource.getRepository(Teacher);
  }

  async findAll(): Promise<Teacher[]> {
    return await this.repository.find();
  }

  async findById(id: number): Promise<Teacher | null> {
    return await this.repository.findOneBy({ id });
  }

  async create(Teacher: Teacher): Promise<Teacher> {
    return await this.repository.save(Teacher);
  }

  async update(id: number, Teacher: Teacher): Promise<Teacher | null> {
    const existingTeacher = await this.findById(id);
    if (!existingTeacher) return null;
    await this.repository.update(id, Teacher);
    return this.findById(id);
  }

  async delete(id: number): Promise<boolean> {
    const result = await this.repository.delete(id);
    return result.affected !== 0;
  }
}
