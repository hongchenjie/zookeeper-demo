package org.study.zookeeper.cfg;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

@Slf4j
public class ZookeeperLock {

    private CuratorFramework curatorFramework;

    private String KEY_PREFIX = "/curator/lock/";
    private String key;

    private InterProcessMutex mutex;

    public ZookeeperLock(CuratorFramework curatorFramework, String key) {
        this.curatorFramework = curatorFramework;
        this.key = key;
        mutex = new InterProcessMutex(curatorFramework, KEY_PREFIX + key);
    }

    public ZookeeperLock() {
    }

    public void acquire() {
        try {
            mutex.acquire();
            log.debug(key + "获取锁");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public void release() {
        try {
            mutex.release();
            log.debug(key + "释放锁");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
