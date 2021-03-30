package com.ns.wx.testspringbootzookeeper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.zookeeper.service.ZookeeperTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestSpringBootZookeeperApplicationTests {

    @Resource
    private ZookeeperTemplate zookeeperTemplate;

    @Test
    void createPersistentNode() {
        String persistentNode = zookeeperTemplate.createPersistentNode("/test/wx", 0);
        System.out.println(persistentNode);
        getChild("/");
    }

    @Test
    void createPersistentSequentialNode() {
        String persistentNode = zookeeperTemplate.createPersistentSequentialNode("/test/wx2", 0);
        System.out.println(persistentNode);
        getChild(persistentNode);
    }

    @Test
    void createEphemeralNode() {
        String persistentNode = zookeeperTemplate.createEphemeralNode("/test/wx3", 0);
        System.out.println(persistentNode);
        getChild("/");
    }

    @Test
    void createEphemeralSequentialNode() {
        String persistentNode = zookeeperTemplate.createEphemeralSequentialNode("/test/wx3", 0);
        System.out.println(persistentNode);
        getChild("/");
    }

    public void getChild(String path){
        System.out.println(path);
        List<String> nodeList = zookeeperTemplate.getNodeList(path);
        for(String tmp:nodeList){
            if(!path.endsWith("/")){
                path+="/";
            }
            getChild(path+tmp);
        }
    }

}
