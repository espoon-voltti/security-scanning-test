package org.camel.test;

import org.apache.camel.CamelContext;
import org.apache.camel.spi.UuidGenerator;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.apache.camel.support.DefaultUuidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Beans that are not implemented in the project are defined here.
 */
@Configuration
public class CamelConfiguration {
    
    @Autowired
    CamelContext camelContext;

    /**
     * Additional context configurations.
     * 
     * @return CamelContextConfiguration
     */
    @Bean
    CamelContextConfiguration camelContextConfiguration() {
        
        return new CamelContextConfiguration() {
            
            public void beforeApplicationStart(CamelContext camelContext) {
                camelContext.setAutoStartup(true);
                camelContext.disableJMX();
            }
            
            public void afterApplicationStart(CamelContext camelContext) {
                
            }
        };
    }
    
    /**
     * UUID generator for routes.
     * 
     * @return UuidGenerator
     */
    @Bean
    public UuidGenerator uuidGenerator() {
        return new DefaultUuidGenerator();
    }
}
