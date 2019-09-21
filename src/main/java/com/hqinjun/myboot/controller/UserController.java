package com.hqinjun.myboot.controller;

import com.hqinjun.myboot.domain.User;
import com.hqinjun.myboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HashMap<String, Object> map, Model model){
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HashMap<String, Object> map, Model model){
        return "login";
    }


//    @ApiOperation(value="add", notes="add a new user")
//    @ApiImplicitParam(name = "id", value = "user id", required = true, dataType = "String", paramType = "String")
    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String userAdd(@PathVariable String id,HashMap<String, Object> map, Model model){

        return "user/useradd";
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String userSelect(HashMap<String, Object> map, Model model){

        return "user/userselect";
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String userUpdate(@PathVariable String id,HashMap<String, Object> map, Model model){

        return "user/userupdate";
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String userDetele(@PathVariable String id,HashMap<String, Object> map, Model model){

        return "user/userdelete";
    }

    @RequestMapping(value = "/getuser/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getuser(@PathVariable int id){
        User user = userRepository.getOne(id);
        return user.getUsername();
    }

}
