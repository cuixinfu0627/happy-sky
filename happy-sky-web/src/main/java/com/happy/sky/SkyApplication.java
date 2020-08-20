package com.happy.sky;

import com.thebeastshop.forest.springboot.annotation.ForestScan;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@MapperScan("com.happy.sky.dao")
@ForestScan(basePackages = "com.happy.sky.forest")
@SpringBootApplication(scanBasePackages = {"com.happy.sky","com.happy.sky.common"})
public class SkyApplication {

    private static final Logger logger = LoggerFactory.getLogger(SkyApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(SkyApplication.class);
        Map<String, Object> defProperties =  new HashMap<>();
        defProperties.put("spring.profiles.default", "local");
        app.setDefaultProperties(defProperties);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        logger.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "External: \t{}://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());
    }

}

