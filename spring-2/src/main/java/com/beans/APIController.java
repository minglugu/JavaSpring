package com.beans;

import org.springframework.stereotype.Controller;

/**
 * @author Minglu Gu
 * @version 1.0
 */
@Controller
public class APIController {
    public void sayHi() {
        System.out.println("你好！API Controller.");
    }
}
