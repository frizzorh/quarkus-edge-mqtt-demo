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
    @ConfigProperty(name = "kafka.bootstrap")
    String kafkaBootstrap;
    @ConfigProperty(name = "kafka.topic")
    String kafkaTopic;

    @Override
    public void configure() throws Exception {

        from(getMqttUri())
                .to(getKafkaUri());
    }

    private String getKafkaUri() {
        return "kafka:" + topic + "?brokers=" + kafkaBootstrap;
    }

    private String getMqttUri() {
        return "paho:" +
                topic +
                "?brokerUrl=tcp://" + mqttBrokerHostname + ":" + mqttBrokerPort;
    }
}
