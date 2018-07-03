package com.goosen.commons.model.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 响应实体父类
 * @author Goosen
 * 2018年6月27日 -下午2:50:50
 */
public abstract class BaseResp implements Serializable {
	
	private static final long serialVersionUID = -7491215402569546437L;
	
	@ApiModelProperty(value = "创建时间",example="2018-06-29 18:11:08")
	private String createTime;
	
	@ApiModelProperty(value = "修改时间",example="2018-06-29 18:11:08")
	private String updateTime;

	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}
