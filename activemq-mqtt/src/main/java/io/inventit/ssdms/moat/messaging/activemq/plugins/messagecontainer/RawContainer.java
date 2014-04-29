package io.inventit.ssdms.moat.messaging.activemq.plugins.messagecontainer;

import java.util.Map;

import org.apache.activemq.broker.BrokerContext;
import org.apache.activemq.transport.mqtt.moat.MoatMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RawContainer implements MoatContainer {
	private static final Logger LOG = LoggerFactory.getLogger(RawContainer.class);
	
	private RawMessageConverter converter = new RawMessageConverter();

	@Override
	public void initContainer(BrokerContext context, Map<String, String> param) {
		LOG.debug("calling initContainer: context: {} param: {}",context, param);
		initConverter(context);
	}

	@Override
	public MoatMessageConverter initConverter(BrokerContext context) {
		LOG.debug("calling initConverter: context: {} ",context);
		return converter.initConverter(context);
	}

	@Override
	public byte[] convertFromMoatMessage(String clientId, byte[] moatPayload) {
		LOG.debug("calling convertFromMoatMessage: clientId: {} ",clientId);
		return converter.convertFromMoatMessage(clientId, moatPayload);
	}

	@Override
	public byte[] convertFromActiveMQMessage(String clientId, byte[] amqPayload) {
		LOG.debug("calling convertFromActiveMQMessage: clientId: {} ",clientId);
		return converter.convertFromActiveMQMessage(clientId, amqPayload);
	}

}
