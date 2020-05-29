package com.chaos.jxls.examples;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OtherBuyers implements Serializable {

    private static final long serialVersionUID = 371369861620341256L;

    @ApiModelProperty("买受人编号")
    private Long otherBuyersId;

    @ApiModelProperty("交易类型 2-认购 3-成交")
    private Integer type;

    @ApiModelProperty("买受人姓名")
    private String buyersName;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("身份证")
    private String idNumber;

    @ApiModelProperty("性别 0-未知 1-男 2-女")
    private Integer gender;

    @ApiModelProperty("与主策人关系")
    private String relationship;
}