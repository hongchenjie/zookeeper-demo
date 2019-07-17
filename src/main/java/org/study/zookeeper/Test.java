package org.study.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class Test {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(100, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
        client.start();

        //锁节点路径必须以/开始
        InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");

        mutex.acquire();
        System.out.println("拿到锁");

        mutex.release();
        System.out.println("释放锁");

        //client.close();

    }
}
