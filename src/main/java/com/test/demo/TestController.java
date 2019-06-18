package com.test.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.demo.constant.ErrorConstant;
import com.test.demo.constant.ErrorEnum;
import com.test.demo.domain.IdentityInfo;
import com.test.demo.domain.Student;
import com.test.demo.group.AddValid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello")
    public Student test(@Validated(AddValid.class)Student student){
        log.info(JSON.toJSONString(student));

        log.error(ErrorEnum.FRIST_ERROR_CODE.getMsg());
        log.error(ErrorEnum.FRIST_ERROR_CODE.getCode());


        log.info(ErrorConstant.STUDENTSCHOOL);
        log.info(ErrorConstant.PROFESSION +"");


        Student student1 = new Student();
        student1.setName("testName");
        student1.setAge(18);
        return student1;
    }


    public static void main(String[] args) {

        IdentityInfo identityInfo = new IdentityInfo();

        String string = "{\"IdentityInfo\":{\"AccountID\":10004011623,\"Username\":\"15801359578\",\"verified\":1,\"DeviceID\":\"5f7266cb\",\"location\":\"cn\",\"CurtName\":\"15801359578\"}}";

        JSONObject jsonObject = JSON.parseObject(string);

        log.error(jsonObject.toJSONString());

        JSONObject info = jsonObject.getJSONObject("IdentityInfo");

        identityInfo = JSON.parseObject(info.toJSONString(),IdentityInfo.class);

    }

}
