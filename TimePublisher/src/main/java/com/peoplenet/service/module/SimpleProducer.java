package com.peoplenet.service.module;

import org.apache.kafka.clients.producer.*;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Properties;


public class SimpleProducer {
    public static final Logger LOGGER = Logger.getLogger(SimpleProducer.class); 
    private final Producer<String, String> producer;

    public SimpleProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:5900");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<String, String>(props);
    }

    public void sendWithoutCallback(String topic, String value) {

        producer.send(new ProducerRecord<String, String>(topic, value));
    }

    public void sendWithCallback(String topic, String value) {

        producer.send(new ProducerRecord<String, String>(topic, value), new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception == null) {
                    LOGGER.debug(String.format("Checksum %s,offset %d,partition %d,timestamp %d",
                            metadata.checksum(), metadata.offset(), metadata.partition(), metadata.timestamp()));
                } else {
                    exception.printStackTrace();
                }
            }
        });
    }

    public void close() {
        producer.close();
    }


    public static void start(List<String> lstTC) {
        SimpleProducer simpleProducer = null;
        try {
            simpleProducer = new SimpleProducer();

            for(String tc : lstTC){
                simpleProducer.sendWithCallback("kafkatopic", tc);
            }

            //give sometime for callback
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            simpleProducer.close();
        }
    }

}

/*
NOTES
/* Simple Producer with No Callback Implementation
for (int i = 0; i < 100; i++)
    simpleProducer.sendWithoutCallback("kafkatopic", Integer.toString(i));
*/