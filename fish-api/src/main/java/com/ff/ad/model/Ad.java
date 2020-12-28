package com.ff.ad.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.ff.common.base.BaseModel;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yuzhongxin
 * @since 2017-09-26
 */
@TableName("tb_ad")
public class Ad extends BaseModel {

    private static final long serialVersionUID = 1L;

	@TableId(value="ad_id", type= IdType.AUTO)
	private Integer adId;

	@TableField("image_url")
	private String imageUrl;
	private Integer status;




	public Integer getAdId() {
		return adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
