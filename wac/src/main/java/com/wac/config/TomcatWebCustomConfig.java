//package com.wac.config;
//
//import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TomcatWebCustomConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
//
//    @SuppressWarnings("deprecation")
//    @Override
//    public void customize(TomcatServletWebServerFactory factory) {
//        factory.addConnectorCustomizers((TomcatConnectorCustomizer)
//                connector -> connector.setAttribute("relaxedQueryChars", "<>[\\]^`{|}"));
//    }
//}
