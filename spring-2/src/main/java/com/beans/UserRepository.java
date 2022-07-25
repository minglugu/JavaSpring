package com.beans;

import org.springframework.stereotype.Repository;

/**
 * @author Minglu Gu
 * @version 1.0
 */
@Repository
public class UserRepository {
    public void sayHi() {
        System.out.println("你好，repo！");
    }
}
