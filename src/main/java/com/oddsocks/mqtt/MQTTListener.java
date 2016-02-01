package com.oddsocks.mqtt;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
public class MQTTListener implements MqttCallback {

	private final Log logger = LogFactory.getLog(getClass());

	@Override
	public void connectionLost(Throwable throwable) {
		logger.info("ERROR: Lost Connection. {}", throwable);
	}

	@Override
	public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
		logger.info("Topic: " + topic);
		logger.info(new String(mqttMessage.getPayload()));
		logger.info("QoS: " + mqttMessage.getQos());
		logger.info("Retained: " + mqttMessage.isRetained());
	}

	@Override
	public void deliveryComplete(final IMqttDeliveryToken iMqttDeliveryToken) {
		// When message delivery was complete
	}

}
