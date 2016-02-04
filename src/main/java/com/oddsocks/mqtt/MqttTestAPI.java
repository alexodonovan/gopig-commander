package com.oddsocks.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mqtt/test")
public class MqttTestAPI {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final String COMMANDS_TOPIC = "gopig/commands";

	@Autowired
	private MqttPublisher mqttPublisher;

	@RequestMapping("/publish")
	public String publish() throws MqttException {
		mqttPublisher.publish(COMMANDS_TOPIC, "message".getBytes(), 0, false);
		return "Message Published";
	}

	@RequestMapping("/forward")
	public String forward() throws MqttException {
		logger.info("Sending forward...");
		String json = "{\"clzType\":\"ForwardCommand\"}";
		mqttPublisher.publish(COMMANDS_TOPIC, json.getBytes(), 0, false);
		return "Forward Published";
	}

	@RequestMapping("/stop")
	public String stop() throws MqttException {
		String json = "{\"clzType\":\"StopCommand\"}";
		mqttPublisher.publish(COMMANDS_TOPIC, json.getBytes(), 0, false);
		return "Stop Published";
	}

}
