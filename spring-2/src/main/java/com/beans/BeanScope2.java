package com.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Minglu Gu
 * @version 1.0
 */
@Component
public class BeanScope2 {

    @Autowired
    private User user3;

    public User getUser() {
        return user3;
    }
}
