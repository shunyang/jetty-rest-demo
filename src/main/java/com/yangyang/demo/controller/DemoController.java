package com.yangyang.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yangyang.demo.bean.Demo;
import com.yangyang.demo.common.HttpContextHelper;
import com.yangyang.demo.common.RespCode;

/**
 * Created by chenshunyang on 16/7/12.
 */
@RestController
public class DemoController {

    private static final Logger Log = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> demo(HttpServletRequest request, HttpServletResponse response) {
        try {
        	Demo demo = new Demo();
        	demo.setId(1l);
        	demo.setName("demo");
            return HttpContextHelper.buildResponse(RespCode.OK,demo);
        } catch (Exception e) {
            Log.error("server error", e);
            return HttpContextHelper.buildResponse(RespCode.SERVER_ERROR);
        }
    }

}
