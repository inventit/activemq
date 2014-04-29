package io.inventit.ssdms.moat.messaging.activemq.plugins.messagecontainer;

import org.apache.activemq.broker.BrokerContext;
import org.apache.activemq.transport.mqtt.moat.MoatMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RawMessageConverter implements MoatMessageConverter {
	private static final Logger LOG = LoggerFactory.getLogger(RawMessageConverter.class);

	@Override
	public MoatMessageConverter initConverter(BrokerContext context) {
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
