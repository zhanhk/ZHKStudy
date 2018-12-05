package com.zhk.study.zuul2.backend.service;

import com.zhk.util.DateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc:
 * @Author: zhanhk
 * @Date: Created in 上午11:45 18/11/22
 * @copyright Navi WeCloud
 */
@RestController
@RequestMapping
public class BackendService {

    @ResponseBody
    @RequestMapping(value = "/getA")
    public Response getA() {
        Response response = new Response();
        response.setTime(DateUtil.getNowDate("yyyy-MM-dd HH:mm:ss"));
        return response;
    }

    public class Response {
        String time ;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
