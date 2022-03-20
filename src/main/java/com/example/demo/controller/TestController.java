package com.example.demo.controller;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.demo.entity.RunoobTblEntity;
import com.example.demo.mapper.RunoobTblMapper;
import com.example.demo.service.RunoobTblService;
import com.example.demo.test.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @RequestMapping("/list")
    @ResponseBody
    public List<RunoobTblEntity> list(){
        return RunoobTblService.list();
    }
}
