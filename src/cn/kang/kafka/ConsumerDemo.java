package cn.kang.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

public class ConsumerDemo {
	private static final String topic = "mygirls";
	private static final int thread = 1;
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("zookeeper.connect", "weekend05:2181,weekend06:2181,weekend07:2181");
		props.put("group.id", "1111");// 消费者组为1111
		props.put("auto.offset.reset", "smallest"); // 自动重置偏移量，
		
		ConsumerConfig config = new ConsumerConfig(props);
		ConsumerConnector consumer = Consumer.createJavaConsumerConnector(config);
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, thread);
		
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
		
		for (final KafkaStream<byte[], byte[]> kafkaStream : streams) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (MessageAndMetadata<byte[], byte[]> mm : kafkaStream) {
						String msg = new String(mm.message());
						System.out.println(msg);
					}
				}
				
			}).start();
		}
		
	}
}
