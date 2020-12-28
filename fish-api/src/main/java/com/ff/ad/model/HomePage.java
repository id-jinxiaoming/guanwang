package com.ff.ad.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.ff.common.base.BaseModel;

@TableName("tb_home_page")
public class HomePage extends BaseModel {

    private static final long serialVersionUID =1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("map_image")
    private String mapImage;

    @TableField("strategic_cooperation")
    private String strategicCooperation;

    @TableField("qr_code_one")
    private String qrCodeOne;

    @TableField("qr_code_two")
    private String qrCodeTwo;

    @TableField("qr_code_three")
    private String qrCodeThree;

    @TableField("qr_code_four")
    private String qrCodeFour;

    @TableField("title_one")
    private String titleOne;

    @TableField("title_two")
    private String titleTwo;

    @TableField("title_three")
    private String titleThree;

    @TableField("title_four")
    private String titleFour;

    @TableField("logo_image")
    private String logoImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMapImage() {
        return mapImage;
    }

    public void setMapImage(String mapImage) {
        this.mapImage = mapImage;
    }

    public String getStrategicCooperation() {
        return strategicCooperation;
    }

    public void setStrategicCooperation(String strategicCooperation) {
        this.strategicCooperation = strategicCooperation;
    }

    public String getQrCodeOne() {
        return qrCodeOne;
    }

    public void setQrCodeOne(String qrCodeOne) {
        this.qrCodeOne = qrCodeOne;
    }

    public String getQrCodeTwo() {
        return qrCodeTwo;
    }

    public void setQrCodeTwo(String qrCodeTwo) {
        this.qrCodeTwo = qrCodeTwo;
    }

    public String getQrCodeThree() {
        return qrCodeThree;
    }

    public void setQrCodeThree(String qrCodeThree) {
        this.qrCodeThree = qrCodeThree;
    }

    public String getQrCodeFour() {
        return qrCodeFour;
    }

    public void setQrCodeFour(String qrCodeFour) {
        this.qrCodeFour = qrCodeFour;
    }

    public String getTitleOne() {
        return titleOne;
    }

    public void setTitleOne(String titleOne) {
        this.titleOne = titleOne;
    }

    public String getTitleTwo() {
        return titleTwo;
    }

    public void setTitleTwo(String titleTwo) {
        this.titleTwo = titleTwo;
    }

    public String getTitleThree() {
        return titleThree;
    }

    public void setTitleThree(String titleThree) {
        this.titleThree = titleThree;
    }

    public String getTitleFour() {
        return titleFour;
    }

    public void setTitleFour(String titleFour) {
        this.titleFour = titleFour;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }
}
