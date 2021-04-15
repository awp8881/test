package com.example.demo.controller;



import com.example.message.produce.MQUtil;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class MallWebController {

    @Autowired
    private RestTemplate template;

//    @Value("${posyspath}")
//    private String poSysPath;
//    @Value("${popassword}")
//    private String poPassWord;





    @RequestMapping("list/get/")
    public String list(@RequestParam("search") String search, ModelMap modelMap){
        Map<String,String> map = new HashMap<String,String>();
        map.put("search",search);
        String url = "http://192.168.249.132:10000/list/get/?search={search}";
        try {
            ResponseEntity<String> response = template.getForEntity(url, String.class,map);
            return response.getBody();
        } catch (Exception e) {
            System.out.println("error happens: {}"+e);
        }
        return  null;
    }


    @RequestMapping("/word")
    public ModelAndView  index(HttpServletRequest request, Map<String,Object> map){
        //--- PageOffice的调用代码 开始 -----
        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        poCtrl.setServerPage("/poserver.zz");//设置授权程序servlet
        poCtrl.setZoomSealServer("http://127.0.0.1:8001/poserver.zz");
        poCtrl.addCustomToolButton("保存","Save",1); //添加自定义按钮
        poCtrl.addCustomToolButton("插入印章", "InsertSeal", 2);//打开文件
        poCtrl.addCustomToolButton("签字", "AddHandSign", 3);
        poCtrl.setSaveFilePage("/save");//设置保存的action
        poCtrl.webOpen("d:\\test.doc", OpenModeType.docAdmin,"admin");
        map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
        //--- PageOffice的调用代码 结束 -----
        ModelAndView mv = new ModelAndView("main");
        return mv;
    }

    @RequestMapping("/save")
    public void saveFile(HttpServletRequest request, HttpServletResponse response){
        FileSaver fs = new FileSaver(request, response);
        fs.saveToFile("d:\\" + fs.getFileName());
        fs.close();
    }

//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        com.zhuozhengsoft.pageoffice.poserver.Server poserver = new com.zhuozhengsoft.pageoffice.poserver.Server();
//        //设置PageOffice注册成功后,license.lic文件存放的目录
//        poserver.setSysPath(poSysPath);
//        ServletRegistrationBean srb = new ServletRegistrationBean(poserver);
//        srb.addUrlMappings("/poserver.zz");
//        srb.addUrlMappings("/posetup.exe");
//        srb.addUrlMappings("/pageoffice.js");
//        srb.addUrlMappings("/jquery.min.js");
//        srb.addUrlMappings("/pobstyle.css");
//        srb.addUrlMappings("/sealsetup.exe");
//        return srb;
//        //
//    }
//
//    /**
//     * 添加印章管理程序Servlet（可选）
//     * @return
//     */
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean2() {
//        com.zhuozhengsoft.pageoffice.poserver.AdminSeal adminSeal = new com.zhuozhengsoft.pageoffice.poserver.AdminSeal();
//        adminSeal.setAdminPassword(poPassWord);//设置印章管理员admin的登录密码
//        //设置印章数据库文件poseal.db存放目录，该文件在当前demo的“集成文件”夹中
//        adminSeal.setSysPath(poSysPath);
//        ServletRegistrationBean srb = new ServletRegistrationBean(adminSeal);
//        srb.addUrlMappings("/adminseal.zz");
//        srb.addUrlMappings("/sealimage.zz");
//        srb.addUrlMappings("/loginseal.zz");
//        return srb;//
//    }
}
