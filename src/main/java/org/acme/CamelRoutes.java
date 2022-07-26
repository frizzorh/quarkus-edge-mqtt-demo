package org.acme;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "mp.messaging.incoming.devices.topic")
    String topic;
    @ConfigProperty(name = "mp.messaging.incoming.devices.host")
    String mqttBrokerHostname;
    @ConfigProperty(name = "mp.messaging.incoming.devices.port")
    String mqttBrokerPort;

    @Override
    public void configure() throws Exception {
        from("timer:foo?period=1000")
                .log("Hello World");

        from(getUri())
                .log("mqtt");
    }

    private String getUri() {
        //return "paho:{{mp.messaging.incoming.device-temp.topic}}?brokerUrl=tcp://{{mp.messaging.incoming.device-temp.host}}:{{mp.messaging.incoming.device-temp.port}}";

        return "paho:" +
                topic +
                "?brokerUrl=tcp://" + mqttBrokerHostname + ":" + mqttBrokerPort;
    }
}
