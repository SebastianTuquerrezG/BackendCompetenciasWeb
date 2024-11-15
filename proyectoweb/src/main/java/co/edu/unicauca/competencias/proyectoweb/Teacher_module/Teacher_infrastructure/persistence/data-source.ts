import { DataSource } from 'typeorm';
import { Teacher } from '../../Teacher_core/entities/Teacher';

export const AppDataSource = new DataSource({
    type: 'mysql',
    host: 'localhost',
    port: 3306,
    username: 'root',
    password: '1121',
    database: 'teacher',
    synchronize: true,
    logging: false,
    entities: [Teacher],
    migrations: [],
    subscribers: [],
});
