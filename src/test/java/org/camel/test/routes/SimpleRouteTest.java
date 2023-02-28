package org.camel.test.routes;

import org.camel.test.Application;
import org.junit.jupiter.api.Test;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.camel.test.spring.junit5.EnableRouteCoverage;
import org.apache.camel.test.spring.junit5.UseAdviceWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.apache.camel.builder.AdviceWith.adviceWith;

@ActiveProfiles("junit")
@CamelSpringBootTest
@SpringBootTest(classes = Application.class, properties = {"camel.springboot.java-routes-include-pattern=**/SimpleRoute*"})
@EnableRouteCoverage
@UseAdviceWith
public class SimpleRouteTest {

    private static final Logger logger = LoggerFactory.getLogger(SimpleRouteTest.class);

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private ProducerTemplate producerTemplate;

    @EndpointInject(uri = "mock:out")
    MockEndpoint mockOut;

    @Test
    public void testSimpleRouteTimer() throws Exception {
        adviceWith(
            camelContext, 
            "SimpleRoute.timer", advice -> {
            advice.replaceFromWith("direct:hitme");
            advice.weaveAddLast().to("mock:out");
        });
        camelContext.start();
        //ensure that all direct routes are called
        mockOut.expectedMessageCount(1);
        producerTemplate.sendBody("direct:hitme", "");
        mockOut.assertIsSatisfied();
    }
}
