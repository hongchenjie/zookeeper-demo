package org.study.zookeeper.service;

import org.springframework.stereotype.Service;
import org.study.zookeeper.model.User;

@Service
public class UserService {

    public User update(Integer id, String name) {
        return new User(id, name);
    }
}
