package org.study.zookeeper.controller;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.zookeeper.cfg.ZookeeperLock;
import org.study.zookeeper.model.User;
import org.study.zookeeper.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private CuratorFramework curatorFramework;

    @Autowired
    private UserService userService;

    //http://localhost:8080/user/update?id=1&name=lipo
    @RequestMapping("/update")
    public Object update(Integer id, String name) {
        ZookeeperLock zookeeperLock = new ZookeeperLock(curatorFramework, "user@update@" + id);
        try {
            zookeeperLock.acquire();
            User update = userService.update(id, name);
            return update;
        } finally {
            zookeeperLock.release();
        }

    }
}
