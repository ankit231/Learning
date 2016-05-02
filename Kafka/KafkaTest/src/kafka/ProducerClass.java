package kafka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import utility.DataReader;
import utility.Person;

import com.google.gson.Gson;

public class ProducerClass {

    static String TOPIC = "AnkitTestTopic";

    public static void main(String b[]) {
        
        System.out.println("Stating Reading json file");
        DataReader dfr = new DataReader();
        ArrayList<Person> persons = dfr.parseJsonFile();
        System.out.println("File read successfully");
        
        
        System.out.println("Staring publishing records");
        ProducerClass myProducer = new ProducerClass();
        myProducer.publishRecords(persons);
    }

    // Producer
    private void publishRecords(ArrayList<Person> persons) {
        Producer<String, String> producer = this.getProducer();

        Integer PARTITION_NUM = 0;
        try {
            File file = new File("/home/ankit/software/kafka_2.11-0.9.0.1/producerfiles/" + TOPIC);
            FileOutputStream fos = new FileOutputStream(file);
            PrintStream ps = new PrintStream(fos);
            for (Person p : persons) {
                if ("male".equals(p.getGender())) {
                    PARTITION_NUM = 0;
                } else {
                    PARTITION_NUM = 1;
                }
                try {
                    ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC, PARTITION_NUM,
                            p.getIndex(), new Gson().toJson(p, Person.class));
                    Future<RecordMetadata> future = producer.send(record);
                    RecordMetadata recordMetaData = future.get(3, TimeUnit.SECONDS);
                    this.printRecordInfile(recordMetaData, ps);
                } catch (Exception e) {
                    System.out.println("Some Exception Occured" + e);
                }
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found" + fnf);
        } finally {
            producer.close();
        }
    }

    private Producer<String, String> getProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("timeout.config", 10000);
        props.put("retries", 0);
        props.put("block.on.buffer.full", true);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        return producer;
    }

    private void printRecordInfile(RecordMetadata recordMetaData, PrintStream ps) {
        System.setOut(ps);
        System.out.format("Record successfully pushed in topic %s, partition %d, at offset %d%n",
                recordMetaData.topic(), recordMetaData.partition(), recordMetaData.offset());

    }
}
