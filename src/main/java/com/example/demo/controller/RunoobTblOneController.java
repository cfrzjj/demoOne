package com.example.demo.controller;

import com.example.demo.entity.RunoobTblEntity;
import com.example.demo.entity.RunoobTblOne;
import com.example.demo.mapper.RunoobTblMapper;
import com.example.demo.mapper.RunoobTblOneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fanrongchen
 * @since 2022-03-20
 */
@Controller
@RequestMapping("/runoobTblOne")
@ResponseBody
public class RunoobTblOneController {

    @Autowired(required = false)
    private RunoobTblOneMapper runoobTblOneMapper;

    @RequestMapping("/add")
    public String add(){
        RunoobTblOne runoobTblEntity = new RunoobTblOne();
        runoobTblEntity.setRunoobAuthor("author");
        runoobTblEntity.setRunoobTitle("tittle");
        runoobTblOneMapper.insert(runoobTblEntity);
        return "====";
    }

    @RequestMapping("/get")
    public String get(String id){
        Integer integer = runoobTblOneMapper.checkTableExistsWithSchema("mybatis_plus", "runoob");
        //        RunoobTblOne oneByRunoobIdAfter = runoobTblOneMapper.getOneByRunoobId(id);
        return "====";
    }

    @RequestMapping("/createTable")
    public String createTable(){
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        int day = instance.get(Calendar.DAY_OF_MONTH);
        String tableName = "test" + year + "" + month + "" + day;
        Integer integer = runoobTblOneMapper.checkTableExistsWithSchema("mybatis_plus", tableName);
        if(integer == 0){
            runoobTblOneMapper.createNewTableAndInsertData(tableName);        //        RunoobTblOne oneByRunoobIdAfter = runoobTblOneMapper.getOneByRunoobId(id);
        }
        return "====";
    }
}
