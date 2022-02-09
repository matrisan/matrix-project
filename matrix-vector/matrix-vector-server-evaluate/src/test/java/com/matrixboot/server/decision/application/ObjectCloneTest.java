package com.matrixboot.server.decision.application;

import lombok.Data;
import org.junit.jupiter.api.Test;

/**
 * TODO
 * <p>
 * create in 2022/1/21 9:31 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
class ObjectCloneTest {

    int num = 1000000;

    @Test
    void newObject() throws CloneNotSupportedException {
        User user = new User("123", "456", "789");
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            User user1 = new User("123", "456", "789");
        }
        System.out.println("New - " + (System.currentTimeMillis() - start));
    }

    @Test
    void cloneObject() throws CloneNotSupportedException {
        User user = new User("123", "456", "789");
        long start = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            User clone = user.clone();
        }
        System.out.println("Clone" + (System.currentTimeMillis() - start));
    }

    @Data
    static class User implements Cloneable {
        private String username;
        private String password;
        private String message;

        public User(String username, String password, String message) {
            this.username = username;
            this.password = password;
            this.message = message;
        }

        @Override
        public User clone() throws CloneNotSupportedException {
            return (User) super.clone();
        }
    }


}
