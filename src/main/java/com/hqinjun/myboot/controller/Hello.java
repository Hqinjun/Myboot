package com.hqinjun.myboot.controller;

import com.hqinjun.myboot.dto.JsonDTO;
import com.hqinjun.myboot.utils.RedisUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

@RestController
@RequestMapping("/boot")
public class Hello {
    @Value("${boot.hello}")
    private String holle;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value="holle", notes="hello world")
    @ApiImplicitParam(name = "key", value = "Hqinjun", required = true, dataType = "String", paramType = "String")
    @RequestMapping(value = "/hello/{key}", method = RequestMethod.GET)
    @ResponseBody
    public String getHolle(@PathVariable String key){
        if (key != null && "Hqinjun".equals(key)){
            return "Hqinjun: " + holle;
        }
        return null;
    }

    @RequestMapping(value = "/laoli", method = RequestMethod.GET ,produces = "application/json;charset=UTF-8")
    public JsonDTO getString(){
        JsonDTO json = new JsonDTO();
        json.setJson("lsnmsl");
        return json;
    }

    @RequestMapping(value = "/redis", method = RequestMethod.GET )
    public void getredis(){
        Set<String> str =  redisUtil.keysBySelect("*",0);
        for (String string : str) {
            System.out.println(string);
        }

    }


}
