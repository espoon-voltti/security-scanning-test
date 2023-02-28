package org.camel.test.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@SuppressWarnings({"OperatorWrap"})
@Component
public class SimpleRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        boolean testbool = true;
        if (testbool == true) {
            
        }

        from("timer:foo?period=5000")
            .routeId("SimpleRoute.timer")
            .log("test");
    }
}
