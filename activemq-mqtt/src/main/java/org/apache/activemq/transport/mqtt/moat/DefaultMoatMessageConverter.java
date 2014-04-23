package org.apache.activemq.transport.mqtt.moat;

import org.apache.activemq.broker.BrokerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultMoatMessageConverter implements MoatMessageConverter {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultMoatMessageConverter.class);

	@Override
	public MoatMessageConverter init(BrokerContext context) {
		// Nothing to do
		return this;
	}

	@Override
	public byte[] convertFromMoatMessage(String clientId, byte[] moatPayload) {
		// Nothing to do
		LOG.debug("D: convertFromMoatMessage: Nothing to convert for clid: {}",clientId);
		return moatPayload;
	}

	@Override
	public byte[] convertFromActiveMQMessage(String clientId, byte[] amqPayload) {
		// Nothing to do
		LOG.debug("D: convertFromActiveMQMessage: Nothing to convert for clid: {}",clientId);
		return amqPayload;
	}

}
