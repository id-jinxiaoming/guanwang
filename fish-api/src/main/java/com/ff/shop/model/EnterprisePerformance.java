package com.ff.shop.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.ff.common.base.BaseModel;

@TableName("tb_enterprise_performance")
public class EnterprisePerformance extends BaseModel {

    private static final long serialVersionUID =1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String chinese;

    private String english;

    private String image;

    private Integer position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
