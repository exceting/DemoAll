/**
 * sharemer.com Inc.
 * Copyright (c) 2018-2019 All Rights Reserved.
 */

package demo.websocket;

import demo.websocket.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sunqinwen
 * @version \: Application.java,v 0.1 2018-08-15 16:42
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AppConfig.class);
        app.setWebApplicationType(WebApplicationType.SERVLET);
        app.run(args);
    }
}
