package org.camel.test.routes;

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
}
