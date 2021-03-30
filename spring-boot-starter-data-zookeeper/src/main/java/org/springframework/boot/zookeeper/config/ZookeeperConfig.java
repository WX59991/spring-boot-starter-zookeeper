package org.springframework.boot.zookeeper.config;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.zookeeper.service.ZookeeperTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Slf4j
//标记为配置类
@Configuration
//启动配置属性
@EnableConfigurationProperties(ZookeeperProperties.class)
//保证DemoService接口在
@ConditionalOnClass(ZookeeperTemplate.class)
@ConditionalOnProperty(prefix = "zookeeper",value = "enabled",matchIfMissing = true)
public class ZookeeperConfig {

    @Autowired
    private ZookeeperProperties zookeeperProperties;


    /**
     * 创建zookeeper造作Template
     * @return ZookeeperTemplate
     * @throws IOException io异常
     */
    @Bean(destroyMethod = "destroy")
    //@ConditionalOnMissingBean是修饰bean的一个注解，主要实现的是，当你的bean被注
    // 册之后，如果而注册相同类型的bean，就不会成功，它会保
    // 证你的bean只有一个，即你的实例只有一个，当你注册多个相同的
    // bean时，会出现异常，以此来告诉开发人员。
    @ConditionalOnMissingBean(ZookeeperTemplate.class)
    public ZookeeperTemplate createZookeeperTemplate(){
        ZkClient zookeeper = createZookeeper();
        ZookeeperTemplate zookeeperTemplate=new ZookeeperTemplate(zookeeper);
        return zookeeperTemplate;
    }

    private ZkClient createZookeeper() {
        ZkClient zooKeeper=new ZkClient(zookeeperProperties.getHosts(), zookeeperProperties.getSession_outtime(),zookeeperProperties.getConnect_timeout());
        return zooKeeper;
    }



}
