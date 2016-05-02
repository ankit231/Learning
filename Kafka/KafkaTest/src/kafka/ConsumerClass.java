package kafka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ConsumerClass {
    public static void main(String a[]) {
        System.out.println("Starting consuming records..");
        ConsumerClass consumer = new ConsumerClass();
        consumer.consumeRecords();
        System.out.println("Done reading records");
    }

    public void consumeRecords() {
        int NUM_OF_CONSUMERS = 1;
        ExecutorService excutor = null;
        try {
            excutor = Executors.newFixedThreadPool(NUM_OF_CONSUMERS);
            excutor.execute(new Runnable() {
                public void run() {
                    // System.out.println("Asynchronous task");
                    KafkaConsumer<String, String> consumer = this.getConsumer();
                    ConsumerRecords<String, String> records = consumer.poll(5000);
                    FileOutputStream fos = null;
                    try {
                        File file = new File("/home/ankit/software/kafka_2.11-0.9.0.1/consumerfiles/"
                                + records.partitions().iterator().next());
                        fos = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    PrintStream ps = new PrintStream(fos);
                    for (ConsumerRecord<String, String> record : records) {
                        this.printRecordInfile(record, ps);
                    }
                }

                private void printRecordInfile(ConsumerRecord<String, String> record, PrintStream ps) {
                    System.setOut(ps);
                    System.out.format("Record topic is %s, partition is %d, offset is %d, value is %s%n",
                            record.topic(), record.partition(), record.offset(), record.value());

                }

                private KafkaConsumer<String, String> getConsumer() {
                    String TOPIC = "AnkitTestTopic";
                    String GROUP_ID = "ankit-consumer";

                    Properties props = new Properties();
                    props.put("zookeeper.connect", "127.0.0.1:2181");
                    props.put("group.id", GROUP_ID);
                    props.put("zookeeper.session.timeout.ms", 6000);
                    props.put("zookeeper.sync.time.ms", 200);
                    props.put("auto.commit.interval.ms", 4000);
                    props.put("auto.offset.reset", "smallest");
                    props.put("auto.commit.enable", true);
                    props.put("consumer.timeout.ms", "10000");
                    props.put("bootstrap.servers", "localhost:9092");
                    props.put("enable.auto.commit", true);
                    props.put("session.timeout.ms", 10000);
                    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
                    props.put("key.deserializer", StringDeserializer.class.getName());
                    props.put("value.deserializer", StringDeserializer.class.getName());
                    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
                    /*
                     * TopicPartition partition0 = new TopicPartition(TOPIC, 0);
                     * TopicPartition partition1 = new TopicPartition(TOPIC, 1);
                     */
                    consumer.subscribe(Arrays.asList(TOPIC));
                    return consumer;
                }
            });
        } catch (Exception e) {
            System.out.println("Exception is " + e);
        } finally {
            excutor.shutdown();
        }

    }
}