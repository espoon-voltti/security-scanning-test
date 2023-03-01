package org.camel.test.routes;

import java.util.regex.Pattern;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@SuppressWarnings({"OperatorWrap"})
@Component
public class SimpleRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        for (int i=0; i<10; i++) {
            for (int j=0; i<10; j++) {
                if (false) break;
            }
        }

        from("timer:foo?period=5000")
            .routeId("SimpleRoute.timer")
            .log("test");
    }
    public static boolean is_valid_hex_color(String color) {
        return Pattern.matches("#[0-9a-fA-f]{6}", color);
    }
}
