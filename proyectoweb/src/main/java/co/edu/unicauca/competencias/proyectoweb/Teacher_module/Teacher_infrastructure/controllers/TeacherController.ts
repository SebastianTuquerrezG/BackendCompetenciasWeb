import { Request, Response } from 'express';
import { ITeacherService } from '../../Teacher_service/interfaces/ITeacherService';
import { Teacher } from '../../Teacher_core/entities/Teacher';

export class TeacherController {
  private TeacherService: ITeacherService;

  constructor(TeacherService: ITeacherService) {
    this.TeacherService = TeacherService;
  }

  async getAll(req: Request, res: Response): Promise<void> {
    const Teachers = await this.TeacherService.getAllTeachers();
    res.json(Teachers);
  }

  async getById(req: Request, res: Response): Promise<void> {
    const Teacher = await this.TeacherService.getTeacherById(Number(req.params.id));
    if (Teacher) {
      res.json(Teacher);
    } else {
      res.status(404).json({ message: 'Teacher not found' });
    }
  }

  async create(req: Request, res: Response): Promise<void> {
    const Teacher: Teacher = req.body;
    const newTeacher = await this.TeacherService.createTeacher(Teacher);
    res.status(201).json(newTeacher);
  }

  async update(req: Request, res: Response): Promise<void> {
    const Teacher: Teacher = req.body;
    const updatedTeacher = await this.TeacherService.updateTeacher(Number(req.params.id), Teacher);
    if (updatedTeacher) {
      res.json(updatedTeacher);
    } else {
      res.status(404).json({ message: 'Teacher not found' });
    }
  }

  async delete(req: Request, res: Response): Promise<void> {
    const success = await this.TeacherService.deleteTeacher(Number(req.params.id));
    if (success) {
      res.status(204).send();
    } else {
      res.status(404).json({ message: 'Teacher not found' });
    }
  }
}
