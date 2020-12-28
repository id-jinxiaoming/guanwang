package com.ff.shop.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.ff.common.base.BaseModel;

@TableName("tb_show_performance")
public class ShowPerformance extends BaseModel {

    private static final long serialVersionUID =1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("en_title")
    private String enTitle;

    @TableField("ch_title")
    private String chTitle;

    private Integer type;

    private String describe;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnTitle() {
        return enTitle;
    }

    public void setEnTitle(String enTitle) {
        this.enTitle = enTitle;
    }

    public String getChTitle() {
        return chTitle;
    }

    public void setChTitle(String chTitle) {
        this.chTitle = chTitle;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
