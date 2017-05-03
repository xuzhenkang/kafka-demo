package cn.itcast.kafka;

import java.util.Properties;

public class ConsumerDemo {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("zookeeper.connect", "weekend05:2181,weekend06:2181,weekend07:2181");
	}
}
