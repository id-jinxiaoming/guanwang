package com.ff.shop.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.ff.common.base.BaseModel;

@TableName("tb_news_media")
public class NewsMedia extends BaseModel {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String title;

    private String image;

    private String describe;

    private String brief;

    private Integer type;

    @TableField("create_year")
    private String createYear;

    @TableField("month_day")
    private String monthDay;

    @TableField("is_roof_placement")
    private Integer isRoofPlacement;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreateYear() {
        return createYear;
    }

    public void setCreateYear(String createYear) {
        this.createYear = createYear;
    }

    public String getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(String monthDay) {
        this.monthDay = monthDay;
    }

    public Integer getIsRoofPlacement() {
        return isRoofPlacement;
    }

    public void setIsRoofPlacement(Integer isRoofPlacement) {
        this.isRoofPlacement = isRoofPlacement;
    }
}
