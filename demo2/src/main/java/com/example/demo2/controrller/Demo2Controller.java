package com.example.demo2.controrller;


import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Demo2Controller {


    @Autowired
    private RestTemplate template;

    @GetMapping("wxLogin")
    public Object list(@RequestParam("code")String code){
        Map<String,String> map = new HashMap<String,String>();
        map.put("code",code);
        map.put("appid","wx85b79075bcc5e1f8");
        map.put("secretid","c011237018ad832a24c4ccdbd4b8718c");
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secretid}&js_code={code}&grant_type=authorization_code";
        try {
            ResponseEntity<String> response = template.getForEntity(url, String.class,map);
            return response.getBody();
        } catch (Exception e) {
            System.out.println("error happens: {}"+e);
        }
        return "success";
    }

    @PostMapping("phone")
    public Object phone(@RequestBody Map<String, String> body) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, JSONException {
        String encryptedData = body.get("encryptedData");
        String iv = body.get("iv");
        String sessionKey = body.get("sessionKey");

//        String result = WechatDecryptDataUtil.decryptData(encryptedData,iv,sessionKey);
        AESUtils aes = new AESUtils();
        byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
        if (null != resultByte && resultByte.length > 0) {
            String jsons = new String(resultByte, "UTF-8");
            System.out.println(jsons);
            JSONObject json = new JSONObject(jsons);
            //json解析phoneNumber值
            String phoneNumber = json.getString("phoneNumber");
            System.out.println("phoneNumber：" + phoneNumber);
            if("138808998626".equals(phoneNumber)){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    @GetMapping("list/get/")
    public Object list(@RequestParam("search") String search, ModelMap modelMap, HttpServletRequest request){

        Enumeration<String> reqHeadInfos=request.getHeaderNames();
        System.out.println("获取到客户端所有的请求头信息如下");


        while(reqHeadInfos.hasMoreElements()) {
            String headName=(String)reqHeadInfos.nextElement();
            String headValue=request.getHeader(headName);
            System.out.println(headName+":"+headValue);
        }

        Map <Object,Object> map = new HashMap<>();
        map.put("1","中国");
        map.put("2","111");
        map.put("3",3);
        return map;
    }


    @GetMapping("test01")
    public Object test01(HttpServletRequest request){
        Enumeration<String> reqHeadInfos=request.getHeaderNames();
        System.out.println("获取到客户端所有的请求头信息如下");
        while(reqHeadInfos.hasMoreElements()) {
            String headName=(String)reqHeadInfos.nextElement();
            String headValue=request.getHeader(headName);
            System.out.println(headName+":"+headValue);
        }
        Map <Object,Object> map = new HashMap<>();
        map.put("1","过滤器主动调用");
        return map;
    }

    @GetMapping("index")
    public Object test02(HttpServletRequest request){
        Enumeration<String> reqHeadInfos=request.getHeaderNames();
        System.out.println("获取到客户端所有的请求头信息如下");
        while(reqHeadInfos.hasMoreElements()) {
            String headName=(String)reqHeadInfos.nextElement();
            String headValue=request.getHeader(headName);
            System.out.println(headName+":"+headValue);
        }
        Map <Object,Object> map = new HashMap<>();
        map.put("1","模拟请求权重分发");
        return map;
    }

}
