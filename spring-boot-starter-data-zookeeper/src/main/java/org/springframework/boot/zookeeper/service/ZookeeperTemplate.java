package org.springframework.boot.zookeeper.service;


import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.util.List;

@Slf4j
public class ZookeeperTemplate {

    private ZkClient zkClient;

    public ZookeeperTemplate(ZkClient zkClient){
        this.zkClient=zkClient;
    }

    /**
     * 创建持久节点
     * 持久节点在注册节点的服务端下线后依然会保存在zookeeper中
     * @param path 路径  如果创建路径的父目录不存在会报错，重复创建也会报错
     * @param value 值
     * @return  结果
     */
    public String createPersistentNode(String path,Object value){
        return zkClient.create(path,value, CreateMode.PERSISTENT);
    }

    /**
     * 创建持久有序节点
     * 当客户端断开连接时，znode不会被自动删除，它的名称将附加一个单调递增的数字。
     * @param path 路径
     * @param value 值
     * @return  结果
     */
    public String createPersistentSequentialNode(String path,Object value){
        return zkClient.create(path,value, CreateMode.PERSISTENT_SEQUENTIAL);
    }

    /**
     * 创建临时节点
     * 注册节点服务端下线后节点会自动删除
     * @param path 路径
     * @param value 值
     * @return  结果
     */
    public String createEphemeralNode(String path,Object value){
        return zkClient.create(path,value, CreateMode.EPHEMERAL);
    }

    /**
     * 创建持久节点
     * 当客户机断开连接时，znode将被删除，其名称将附加一个单调递增的数字。
     * @param path 路径
     * @param value 值
     * @return  结果
     */
    public String createEphemeralSequentialNode(String path,Object value){
        return zkClient.create(path,value, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    /**
     * 删除路径,一次只能删除一个路径，不能 删除一个目录
     * @param path 路径
     * @return 是否删除成功 true--成功 false--失败
     */
    public boolean deleteNode(String path){
        return zkClient.delete(path);
    }

    /**
     * 递归删除
     * @param path 待删除路径
     * @return 删除结果
     */
    public boolean deleteRecursive(String path){
        return zkClient.deleteRecursive(path);
    }

    /**
     * 获取 路径列表
     * @param path 路径
     * @return 路径列表
     */
    public List<String>  getNodeList(String path){
        return zkClient.getChildren(path);
    }

    /**
     * 获取子节点个数
     * @param path 路径
     * @return 子节点列表
     */
    public int getChildrenCount(String path){
        return zkClient.countChildren(path);
    }

    /**
     * 判断节点是否存在
     * @param path path
     * @return true-存在 false-不存在
     */
    public boolean isExit(String path){
        return zkClient.exists(path);
    }

    /**
     * 当bean被回收即服务停止时，断开 连接
     * 如果不调用close，在 zookeeper服务端会出现强制断开链接的异常
     */
    public void destroy(){
        try {
            zkClient.close();
            log.info("zookeeper客户端已销毁");
        }catch (Exception e){
            log.error("",e);
        }
    }

}
