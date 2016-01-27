package com.oddsocks.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mqtt/test")
public class MqttTestAPI {

	@RequestMapping("/publish")
	public String publish() throws MqttException {
		MqttClient mqttClient = new MqttClient("tcp://localhost:1883", MqttClient.generateClientId());
		mqttClient.connect();
		mqttClient.publish("my/topic", "message".getBytes(), 0, false);
		mqttClient.disconnect();
		return "Message Published";
	}

}
