package com.example.rabbitmq_project.service;

import com.example.rabbitmq_project.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ListenerConsumer {


    /*
    * @RabbitListener => take queues = array of queues that will listen to it to consumer from them
    *                   and the name of queue is should match the name in yml file like i write
    *                   second i take the factory of listener that i create it in RabbitMqConfig
    *
    *   @RabbitListener => one the Important thing in the listener is when mamic that function fail like we make
    *                       blow that we throw new RunTimeException() then it will not consume the message
    *                       but return it again to the queue because the method is fail to complete successfully
    *
    * */
    @RabbitListener(queues =
            {       "${rabbitmq.fan-queue1}",
                    "${rabbitmq.fan-queue2}",
                    "${rabbitmq.fan-queue3}"}
            ,containerFactory = "simpleRabbitListenerContainerFactory")
    public void receiveMessage(Message message){
        System.out.println(message.toString());
        throw  new RuntimeException();
    }
}
