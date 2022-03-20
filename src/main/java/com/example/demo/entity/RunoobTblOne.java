package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * <p>
 * 
 * </p>
 *
 * @author fanrongchen
 * @since 2022-03-20
 */
@TableName("runoob_tbl_one")
@Data
public class RunoobTblOne implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "runoob_id", type = IdType.ASSIGN_UUID)
    private String runoobId;

    private String runoobTitle;

    private String runoobAuthor;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer deleted;

}
