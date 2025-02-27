import { Client } from 'pg';
import dotenv from 'dotenv';

dotenv.config();

const client = new Client({
  host: process.env.DATABASE_HOST,
  port: 5432,
  user: process.env.DATABASE_USER,
  password: process.env.DATABASE_PASSWORD,
  database: process.env.DATABASE_NAME,
});

client.connect();

export const updateInvestmentStatus = async (investmentId: string) => {
  try {
    const result = await client.query(
      'UPDATE investments SET status = $1 WHERE id = $2 AND status = $3 RETURNING *',
      ['SUCCESS', investmentId, 'PROCESSING']
    );
    if (result.rows.length > 0) {
      console.log(`Investment ${investmentId} updated to success.`);
    } else {
      console.log(`Investment ${investmentId} not found or already updated.`);
    }
  } catch (error) {
    console.error('Error updating investment status:', error);
  }
};