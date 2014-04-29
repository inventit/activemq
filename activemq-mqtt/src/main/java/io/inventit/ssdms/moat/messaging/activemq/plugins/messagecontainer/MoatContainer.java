package io.inventit.ssdms.moat.messaging.activemq.plugins.messagecontainer;

import java.util.Map;

import org.apache.activemq.broker.BrokerContext;
import org.apache.activemq.transport.mqtt.moat.MoatMessageConverter;

public interface MoatContainer extends MoatMessageConverter {
	public void initContainer(BrokerContext context, Map<String, String> param);
}
