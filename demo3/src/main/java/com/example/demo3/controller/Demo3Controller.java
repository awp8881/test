package com.example.demo3.controller;


import com.example.demo3.Error.IdNotFoundException;
import com.example.demo3.IService.IUserService;
import com.example.demo3.annotion.log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class Demo3Controller {

    @Autowired
    IUserService userService;


    @GetMapping("list/get/")
    public Object list(@RequestParam("search") String search, ModelMap modelMap, HttpServletRequest request){

        Enumeration<String> reqHeadInfos=request.getHeaderNames();
        System.out.println("获取到客户端所有的请求头信息如下");


        while(reqHeadInfos.hasMoreElements()) {
            String headName=(String)reqHeadInfos.nextElement();
            String headValue=request.getHeader(headName);
            System.out.println(headName+":"+headValue);
        }

        Map<Object,Object> map = new HashMap<>();
        map.put("1","负载均衡成功");
        map.put("2","负载均衡成功");
        map.put("3","负载均衡成功");
        return map;
    }


    @GetMapping("conCurrentHashMap")
    public void  conCurrentHashMap(){

        Map<Object,Object> map2 = new ConcurrentHashMap<>();
        map2.put("test","11111");
        map2.put("test2","22222");
    }


    @GetMapping("testError")
    public void  testError() {
//        throw IdNotFoundException.byId("1");
        int a = 1/0;
    }

    @GetMapping("annotation")
//    @log(value = "登录事件")
    public Object annotation(){
        userService.updateAge("1","1111");
        String name = "张三";
        Map<Object,Object> map = new HashMap<>();
        map.put("1","负载均衡成功1");
        map.put("2","负载均衡成功2");
        map.put("3","负载均衡成功3");


        //Map集合遍历方式
        Set<Object> set = map.keySet();
        Iterator<Object> iterator =  set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        Collection<Object> collection  = map.values();
        for (Object o : collection) {
            System.out.println(o);
        }

        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        Iterator<Map.Entry<Object, Object>> iterator2 = entries.iterator();
        while (iterator2.hasNext()){
            Object o = iterator2.next();
            Map.Entry entry = (Map.Entry) o;
            System.out.println(entry.getKey()+"===="+entry.getValue());
        }


        Iterator<Map.Entry<Object, Object>> iterator1 = map.entrySet().iterator();
        while (iterator1.hasNext()){
            Map.Entry entry = iterator1.next();
            System.out.println(entry.getKey()+"===="+entry.getValue());
        }


        map.entrySet().stream().forEach(entry->{
            System.out.println(entry.getKey()+"===="+entry.getValue());
        });

        return  name;

    }


    @GetMapping("/Test")
    public Object Test(){

        Date date = new Date();
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = simpleDateFormat.format(date);
        System.out.println(result);
        Map map = new HashMap();
        map.put("today",result);


        //递归
        int aaa = digui(10);
        System.out.println(aaa);

        //递归获取C盘windows下所有文件
        File file  = new File("C:\\Windows");
//        diguiFile(file);

        int sum = rabbit(1);
        System.out.println("兔子数量"+sum);
        return  map;
    }

    private int rabbit(int i) {
        if(i == 1 || i == 2){
            return 1;
        }else {
            return  rabbit(i-1)+rabbit(i-2);
        }

    }

    private int digui(int num){
        if(num == 1){
           return  1;
        }else {
            return  num *digui(num-1);
        }
    }

    private void diguiFile(File file){
        File[] files = file.listFiles();
        if(files != null){
            for (File file1 : files) {
                if(file1.isDirectory()){
                    System.out.println("当前是文件夹，进行递归");
                     diguiFile(file1);
                }else {
                    System.out.println(file1.getName());

                }
            }
        }

    }

}
