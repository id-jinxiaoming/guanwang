package com.ff.ad.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.ff.common.base.BaseModel;

@TableName("tb_small_card")
public class SmallCard extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("small_title")
    private String smallTitle;

    @TableField("big_title")
    private String bigTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSmallTitle() {
        return smallTitle;
    }

    public void setSmallTitle(String smallTitle) {
        this.smallTitle = smallTitle;
    }

    public String getBigTitle() {
        return bigTitle;
    }

    public void setBigTitle(String bigTitle) {
        this.bigTitle = bigTitle;
    }
}