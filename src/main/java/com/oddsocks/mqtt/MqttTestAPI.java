package com.oddsocks.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mqtt/test")
public class MqttTestAPI {

	@Autowired
	private MqttPublisher mqttPublisher;

	@RequestMapping("/publish")
	public String publish() throws MqttException {
		mqttPublisher.publish("my/topic", "message".getBytes(), 0, false);
		return "Message Published";
	}

	@RequestMapping("/forward")
	public String forward() throws MqttException {
		mqttPublisher.publish("my/topic", "forward".getBytes(), 0, false);
		return "Forward Published";
	}

	@RequestMapping("/stop")
	public String stop() throws MqttException {
		mqttPublisher.publish("my/topic", "stop".getBytes(), 0, false);
		return "Stop Published";
	}

}
