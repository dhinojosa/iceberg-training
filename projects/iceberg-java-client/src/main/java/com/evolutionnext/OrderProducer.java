package com.evolutionnext;

import com.ginsberg.gatherers4j.Gatherers4j;
import com.ginsberg.gatherers4j.ShufflingGatherer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.ginsberg.gatherers4j.Gatherers4j.*;

public class OrderProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("schema.registry.url", "http://localhost:8099");

        KafkaProducer<String, Order> orderProducer = new KafkaProducer<>(props);
        KafkaProducer<String, OrderItem> orderItemProducer = new KafkaProducer<>(props);

        AtomicBoolean running = new AtomicBoolean(true);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            running.set(false);
        }));

        Random random = new Random();

        while (running.get()) {
            Integer customerId = random.nextInt(51) + 1;
            String orderId = UUID.randomUUID().toString();
            Order order = new Order(orderId, Instant.now(), customerId);
            Set<Integer> productIds = IntStream.range(1, 100).boxed().gather(shuffle(random))
                .limit(random.nextInt(10) + 1).collect(Collectors.toSet());
            orderProducer.send(new ProducerRecord<>("my_orders", orderId, order));
            productIds.forEach(p -> {
                int quantity = random.nextInt(10) + 1;
                String orderItemId = UUID.randomUUID().toString();
                OrderItem orderItem = new OrderItem(orderItemId, orderId, p, quantity);
                orderItemProducer.send(new ProducerRecord<>("my_order_items", orderItemId, orderItem));
            });
        }

        orderProducer.close();
        orderItemProducer.close();
    }
}
