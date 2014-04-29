package org.apache.activemq.transport.mqtt.moat;

import org.apache.activemq.broker.BrokerContext;

public interface MoatMessageConverter {
	public MoatMessageConverter initConverter(BrokerContext context);
	public byte[] convertFromMoatMessage(String clientId, byte[] moatPayload);
	public byte[] convertFromActiveMQMessage(String clientId, byte[] amqPayload);
}
