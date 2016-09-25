package gm.connectfour.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
public class Controller {
    @RequestMapping("/")        //TODO: remove
    public String hello(){
        int x;
        return String.format("hello %tT", Calendar.getInstance().getTime());
    }
}
