package com.rong360.im.service;

import com.rong360.im.service.remote.HSessionService;
import com.rong360.im.service.remote.HUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.rong360.im.common.CheckArgs.Strings;

/**
 * Created by chengchao on 2017/2/25.
 */
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    private HUserService remoteUser = new HUserService();
    private HSessionService remoteSession = new HSessionService();

    public int login(String username, String password) {
        if (Strings.isAnyEmpty(username, password)) {
            logger.warn("[Login]用户名或密码为空，username：{}, password: {}", username, password);
            return -1;
        }
        //todo
        return remoteUser.login(null, username, null, null);
    }

    public boolean saveSession(int userId, String sessionId) {
        if (Strings.isEmpty(sessionId)) {
            return false;
        }
        //TODO
        return true;
    }

    public void logout(int userId) {
        //TODO

    }
}
