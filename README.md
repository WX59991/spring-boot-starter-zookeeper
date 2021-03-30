# spring-boot-starter-zookeeper
zookeeper整合入spring-boot-start
###使用方式:
* 方法一：把spring-boot-starter-data-zookeeper项目下载下来，打包，
上传到maven私库中，再引入对应的依赖
* 方法二：把spring-boot-starter-data-zookeeper项目直接作为子模块引
入项目，可以参考zoookeeper-test
###配置：
* 1.引入 依赖：
```javascript
       <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-zookeeper</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
```
* 2.项目 中引入zookeeper配置 
```javascript
       zookeeper:
         enabled: true
         hosts: localhost:2181
         session_outtime: 2000
         connect_timeout: 2000
```
* 3.在项目中编写代码
```javascript
    @Resource
    private ZookeeperTemplate zookeeperTemplate;

    public void createPersistentNode() {
        String persistentNode = zookeeperTemplate.createPersistentNode("/test/wx", 0);
        System.out.println(persistentNode);
    }
```
