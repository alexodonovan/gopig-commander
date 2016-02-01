package com.oddsocks.mqtt;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.stereotype.Service;

@Service
public class MqttPublisher {

	private final Log logger = LogFactory.getLog(getClass());

	private MqttClient mqttClient;

	@PostConstruct
	public void init() throws MqttException {
		logger.info("Connecting to MQTT server");
		mqttClient = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
		MqttConnectOptions options = new MqttConnectOptions();
		options.setKeepAliveInterval(5);
		mqttClient.connect(options);
	}

	@PreDestroy
	public void tearDown() throws MqttException {
		mqttClient.disconnect();
	}

	public void publish(String topic, byte[] bytes, int qos, boolean retained) throws MqttPersistenceException, MqttException {
		mqttClient.publish(topic, bytes, qos, retained);
	}

	public void publish(String topic, String payload) throws MqttPersistenceException, MqttException {
		MqttMessage mqttMessage = new MqttMessage(payload.getBytes());
		mqttClient.publish(topic, mqttMessage);
	}

}
