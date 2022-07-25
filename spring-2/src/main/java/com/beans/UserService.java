package com.beans;

import org.springframework.stereotype.Service;

/**
 * @author Minglu Gu
 * @version 1.0
 */

@Service
public class UserService {
    public void sayHi() {
        System.out.println("Hello, Service!");
    }
}
