package com.beans;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

/**
 * @author Minglu Gu
 * @version 1.0
 */
@Configuration
public class UserConfiguration {
    public void sayHi() {
        System.out.println("Hello, config!");
    }
}
