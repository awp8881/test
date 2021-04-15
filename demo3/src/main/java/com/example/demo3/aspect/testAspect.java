package com.example.demo3.aspect;


import com.alibaba.fastjson.JSONObject;
import com.example.demo3.annotion.log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Aspect
public class testAspect {


    @Pointcut("@annotation(com.example.demo3.annotion.log)")
    public void annotatedMethod() {
    }


    @Around("annotatedMethod()")
    public  Object around(ProceedingJoinPoint pjp){

        long beginTime = System.currentTimeMillis();

        MethodSignature ms = (MethodSignature) pjp.getSignature();
        Method method = ms.getMethod();
        //请求参数名称
        String[] parameterNames = ms.getParameterNames();
        List<Object> argList = new ArrayList<>();
        for(Object arg : pjp.getArgs()){

            if(arg instanceof HttpServletRequest){
                argList.add("request 参数:"+ JSONObject.toJSONString(((HttpServletRequest)arg).getParameterMap()));
            }else if (arg instanceof HttpServletResponse) {
                argList.add("response");
            }else if(arg instanceof MultipartFile){
                argList.add("上传文件名称:"+ ((MultipartFile)arg).getOriginalFilename());
            }else if(arg instanceof List){
                //获取list的泛型
                Type clazz = arg.getClass().getGenericSuperclass();
                ParameterizedType pt = (ParameterizedType)clazz;
                Type[] act = pt.getActualTypeArguments();
                try {
                    if(act != null && act.length>0 &&MultipartFile.class.isAssignableFrom(Class.forName(act[0].getTypeName()))){
                        List<String> fileNames = new ArrayList<>();
                        ((List<MultipartFile>)arg).forEach(item -> fileNames.add(item.getOriginalFilename()));
                        argList.add("上传文件名称: "+ fileNames.stream().collect(Collectors.joining(",")));
                    }else{ //其他泛型
                        argList.add(JSONObject.toJSON(arg));
                    }
                } catch (ClassNotFoundException e) {

                }
            }else{
                argList.add(JSONObject.toJSON(arg));
            }
        }

        return null;
    }
}
