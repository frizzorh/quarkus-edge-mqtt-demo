package org.acme;

import io.reactivex.Flowable;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class TempGenerator {

    private Device device;

    public TempGenerator(@ConfigProperty(name = "device.name") String deviceName) {
        this.device = new Device(deviceName);
    }

    @Outgoing("device-temp")
    public Flowable<String> generate() {
        return Flowable.interval(5, TimeUnit.SECONDS)
                .onBackpressureDrop()
                .map(t -> {
                    String data = device.toString();
                    System.out.println(data);
                    return data;
                });
    }

}
