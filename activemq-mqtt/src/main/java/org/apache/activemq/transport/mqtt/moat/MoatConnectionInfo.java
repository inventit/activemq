package org.apache.activemq.transport.mqtt.moat;

import io.inventit.ssdms.moat.messaging.activemq.plugins.messagecontainer.MoatContainer;
import io.inventit.ssdms.moat.messaging.activemq.plugins.messagecontainer.RawContainer;

import java.net.URISyntaxException;
import java.util.Map;

import org.apache.activemq.broker.BrokerContext;
import org.apache.activemq.command.ConnectionInfo;
import org.apache.activemq.util.URISupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class MoatConnectionInfo extends ConnectionInfo {
	private static final Logger LOG = LoggerFactory.getLogger(MoatConnectionInfo.class);
	private String defaultMoatContainerType = "Raw";
	private MoatContainer container = new RawContainer();
	private MoatAuthMode moatAuthMode = MoatAuthMode.getDefault();
	
	public void initMoatContainer(BrokerContext context) {
		LOG.debug("initMoatContainer: context: {}",context);
		if (StringUtils.isEmpty(getUserName()) == false) {
			LOG.debug("initMoatContainer: userName: {}",getUserName());
			if (getUserName().contains("?")) {
				//Query parameter exists
				try {
					Map<String, String> map = URISupport.parseQuery(getUserName());
					String containerType = map.get("c");
					if (containerType != null) {
						LOG.debug("creating container type {}", defaultMoatContainerType);
						setMoatContainer(context, containerType, map);
						return;
					}
				} catch (URISyntaxException e) {
					LOG.warn("Illegal user name format. Ignore.");
				}
			}
		}
		LOG.debug("initMoatContainer: creating default: {}",defaultMoatContainerType);
		setMoatContainer(context, defaultMoatContainerType, null);
	}
	
	private static final String CONTAINER_PACKAGE = "io.inventit.ssdms.moat.messaging.activemq.plugins.messagecontainer.";
	
	private void setMoatContainer(BrokerContext context, String containerType, Map<String, String> map ) {
		String className = CONTAINER_PACKAGE + containerType+"Container";
		try {
			Class<?> klazz = null;
			LOG.debug("creating moat container: className: {}", klazz);
			try {
				klazz = Class.forName(className);
			} catch (ClassNotFoundException e) {
				LOG.debug("creating moat container: defaultMoatContainerType: {}", defaultMoatContainerType);
				String defaultClassName = CONTAINER_PACKAGE + defaultMoatContainerType+"Container";
				klazz = Class.forName(defaultClassName);
			}
			
			container = (MoatContainer)klazz.newInstance();
			if (map != null) {
				container.initContainer(context, map);
			}
			LOG.debug("created container: {}",container);
		} catch (ClassNotFoundException e) {
			LOG.warn("{} not found. Use default.",className);
		} catch (InstantiationException e) {
			LOG.warn("Failed to instanciate {}.",className);
		} catch (IllegalAccessException e) {
			LOG.warn("Failed to access {}.",className);
		} catch (ClassCastException e) {
			LOG.error("Illegal access {}.",className);
		}
	}
	public void setDefaultMessageContainer(String containerType) {
		defaultMoatContainerType = containerType;
	}
	
	public MoatContainer getMoatContainer() {
		return container;
	}
	
	public void setMoatAuthMode(MoatAuthMode mode) {
		this.moatAuthMode = mode;
	}
	
	public MoatAuthMode getMoatAuthMode() {
		return moatAuthMode;
	}
}
