package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.demo.entity.RunoobTblEntity;
import com.example.demo.entity.RunoobTblOne;
import com.example.demo.mapper.RunoobTblMapper;
import com.example.demo.service.RunoobTblService;
import com.example.demo.test.Hello;
import com.mysql.cj.protocol.x.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

@RestController
public class TestController {

    @Resource
    private Hello hello;

    @Autowired(required = false)
    private RunoobTblMapper runoobTblMapper;

    @Autowired(required = false)
    private RunoobTblService RunoobTblService;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @RequestMapping("/fastAutoGenerator")
    public String fastAutoGenerator(){
        String name = this.getClass().getName();
        int index = name.indexOf(".controller");
        String packageName = name.substring(0, index);
        String property = System.getProperty("user.dir");
        String outputDir = property + "/src/main/java";
        String outputXmlDir = property + "/src/main/resources/mapper";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("fanrongchen") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outputDir); // 指定输出目录
//                            .outputDir("F://111"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(packageName) // 设置父包名
//                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, outputXmlDir)); // 设置mapperXml生成路径
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, "F://111")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("runoob_tbl_one") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

        return "====";
    }

    @RequestMapping("/test")
    public String test(){
        hello.sayHello("张三");
        System.out.println("=主线程="+Thread.currentThread().getThreadGroup());
        return "====";
    }

    @RequestMapping("/add")
    public String add(){
        RunoobTblEntity runoobTblEntity = new RunoobTblEntity();
        runoobTblEntity.setRunoobAuthor("author");
        runoobTblEntity.setRunoobTitle("tittle");
        runoobTblMapper.insert(runoobTblEntity);
        return "====";
    }

//    @RequestMapping("/delete")
    @PostMapping("/delete")
    public String delete(@RequestBody RunoobTblEntity runoob){
        RunoobTblService.removeById(runoob);
        return "====";
    }

    @GetMapping("/testGet")
    public String testGet(){
        RestTemplate restTemplate = new RestTemplate();
        //get请求
        String url = "http://localhost:9091/runoobTblOne/test";
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);

        return "====";
    }

    @GetMapping("/testPost")
    public String testPost(){
        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("UTF-8")));
        //get请求
        String url = "http://localhost:9091/runoobTblOne/testPost";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        List<RunoobTblEntity> list = RunoobTblService.list();
        String str = JSONObject.toJSONString(list);
        HttpEntity httpEntity = new HttpEntity(str, headers);
        ResponseEntity<JSONObject> stringResponseEntity = restTemplate.postForEntity(url, httpEntity, JSONObject.class);
        return "====";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<RunoobTblEntity> list(){
        return RunoobTblService.list();
    }
}
