package org.acme;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class TempGenerator {

    Device esp8266 = new Device("ESP8266-01");

    @Outgoing("device-temp")
    public Flowable<String> generate() {
        return Flowable.interval(2, TimeUnit.SECONDS)
                .onBackpressureDrop()
                .map(t -> {
                    String data = esp8266.toString();
                    System.out.println(data);
                    return data;
                });
    }

}
