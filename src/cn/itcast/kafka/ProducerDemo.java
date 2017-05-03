package cn.itcast.kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerDemo {
	public static void main(String[] args) throws InterruptedException {
		Properties props = new Properties();
		props.put("zk.connect", "weekend05:2181,weekend06:2181,weekend07:2181");// Zookeeper集群连接
		props.put("metadata.broker.list",
				"weekend05:9092,weekend06:9092,weekend07:9092"); // broker节点
		props.put("serializer.class", "kafka.serializer.StringEncoder");// 用哪一个类来作为数据类型的序列化，String用StringEncoder来序列化

		ProducerConfig config = new ProducerConfig(props);
		Producer<String, String> producer = new Producer<String, String>(config); // 泛型中，前面的String指的是topic的名字，后面的String指的是topic的消息

		// 发送业务消息
		// 读取文件 读取内存数据库，读socket端口
		for (int i = 1; i <= 20; i++) {
			Thread.sleep(500);
			producer.send(new KeyedMessage<String, String>("mygirls", "I said 'I love you baby!' for " + i + " times."));
		}
	}
}
