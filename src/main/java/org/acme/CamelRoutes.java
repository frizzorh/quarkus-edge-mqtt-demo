package org.acme;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    @ConfigProperty(name = "routes.kafka.camelURI")
    String kafkaURI;

    @ConfigProperty(name = "routes.mqtt.camelURI")
    String mqttURI;

    @Override
    public void configure() {

        from(mqttURI).to(kafkaURI);
    }

}
