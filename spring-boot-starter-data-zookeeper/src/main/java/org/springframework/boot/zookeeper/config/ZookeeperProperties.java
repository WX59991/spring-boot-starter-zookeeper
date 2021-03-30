package org.springframework.boot.zookeeper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "zookeeper")
public class ZookeeperProperties {

    private String hosts;

    private Integer session_outtime;

    private Integer connect_timeout;
}
