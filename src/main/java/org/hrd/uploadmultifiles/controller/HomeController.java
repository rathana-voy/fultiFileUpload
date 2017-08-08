package org.hrd.uploadmultifiles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ratha on 08-Aug-17.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping(value = {"","/"})
    public String homepage(Model model){
        return "home";
    }

}


