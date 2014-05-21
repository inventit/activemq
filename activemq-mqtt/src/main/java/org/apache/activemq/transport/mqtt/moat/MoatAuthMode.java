package org.apache.activemq.transport.mqtt.moat;

public enum MoatAuthMode {
	USER_AND_DEVICE("userAndDevice"),
	DEVICE_ONLY("deviceOnly"),
	TRUST_APP("trustApp");
	
	private String name;
	
	private MoatAuthMode(String name) {
		this.name = name;
	}
	
	public static MoatAuthMode fromName(String name) {
		for (MoatAuthMode mode: MoatAuthMode.values()) {
			if (mode.name.equals(name)) {
				return mode;
			}
		}
		throw new IllegalArgumentException("Unknown auth mode: "+name);
	}
	
	public static MoatAuthMode getDefault() {
		return USER_AND_DEVICE;
	}
}