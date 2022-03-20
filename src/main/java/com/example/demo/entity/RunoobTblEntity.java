package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("runoob_tbl")
public class RunoobTblEntity {

    @TableId(value = "runoob_id", type = IdType.ASSIGN_UUID)
//    @TableId(value = "runoob_id", type = IdType.INPUT)
    private String runoobId;

    private String runoobTitle;

    private String runoobAuthor;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}
