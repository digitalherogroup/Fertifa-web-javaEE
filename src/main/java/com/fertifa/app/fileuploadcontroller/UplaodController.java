package com.fertifa.app.fileuploadcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UplaodController {


    //@CrossOrigin()

    @RequestMapping("/user/register")
    String registerUser() {
        return "Yeeeeeh";
    }
}
