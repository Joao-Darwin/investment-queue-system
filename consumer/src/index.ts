import amqp from 'amqplib';
import dotenv from 'dotenv';
import { updateInvestmentStatus } from './database/db';
import { Investment } from './@types/Investment';

dotenv.config();

const RABBITMQ_URL = process.env.RABBITMQ_URL || "amqp://localhost";
const QUEUE_NAME = process.env.QUEUE_NAME || "queue";

async function consumeMessage() {
  try {
    const connection = await amqp.connect(RABBITMQ_URL);
    const channel = await connection.createChannel();
    await channel.assertQueue(QUEUE_NAME, { durable: true });

    console.log(`Waiting for messages in ${QUEUE_NAME}...`);

    channel.consume(QUEUE_NAME, async (msg) => {
      if (msg) {
        try {
            const investment: Investment = JSON.parse(msg.content.toString());
  
            if (!investment.id || !investment.status) {
              console.error("Invalid investment data:", investment);
              return channel.nack(msg, false, false);
            }
  
            await updateInvestmentStatus(investment.id);
  
            channel.ack(msg);
          } catch (error) {
            console.error("Error processing message:", error);
            channel.nack(msg, false, false);
          }
      }
    });
  } catch (error) {
    console.error('Error in consumer:', error);
  }
}

consumeMessage();