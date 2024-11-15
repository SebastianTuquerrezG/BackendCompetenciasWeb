import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn, UpdateDateColumn } from 'typeorm';

@Entity('teachers')
export class Teacher {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column({ type: 'varchar', length: 50 })
  identificationType!: string;

  @Column({ type: 'varchar', length: 50 })
  teacherType!: string;

  @Column({ type: 'varchar', length: 100 })
  names!: string;

  @Column({ type: 'varchar', length: 100 })
  lastNames!: string;

  @Column({ type: 'varchar', length: 100 })
  identification!: string;

  @Column({ type: 'varchar', length: 100 })
  title!: string;

  @CreateDateColumn()
  created_at!: Date;

  @UpdateDateColumn()
  updated_at!: Date;
}
