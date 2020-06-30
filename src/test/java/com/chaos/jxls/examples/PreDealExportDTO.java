package com.chaos.jxls.examples;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 认购记录导出DTO
 */
@Data
public class PreDealExportDTO implements Serializable {

    @ApiModelProperty("序号")
    private Integer index;

    @ApiModelProperty("认购表id")
    private Long preDealId;

    @ApiModelProperty("审核状态 0.待审核 1.审核不通过 2.审核通过")
    private Integer auditStatus;

    @ApiModelProperty("客户名字")
    private String customerName;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("交易联系￿地址")
    private String address;

    @ApiModelProperty("买受人ids")
    private List<Long> otherBuyersIds;

    @ApiModelProperty("买受人列表")
    private List<OtherBuyers> otherBuyerList;

    @ApiModelProperty("房号")
    private String roomFullName;

    @ApiModelProperty("定金金额")
    private Long preDealMoney;

    @ApiModelProperty("认购时间")
    private Date preDealDate;

    @ApiModelProperty("成交单价")
    private Long unitPrice;

    @ApiModelProperty("成交总价")
    private Long totalPrice;

    @ApiModelProperty("可签约时间")
    private Date contractTime;

    @ApiModelProperty("可贷款银行名称")
    private String bankName;

    @ApiModelProperty("折扣方式,0.无折扣 1.总价减 2.折扣 3.总价减+折扣")
    private Integer discountType;

    @ApiModelProperty("折扣比例")
    private Integer discountPercent;

    @ApiModelProperty("折扣说明")
    private String discountRemark;

    @ApiModelProperty("总价减金额")
    private Long reductionValue;

    @ApiModelProperty("1-按揭 2-分期 3-一次性付清")
    private Integer payType;

    @ApiModelProperty("全款付款金额")
    private Long payment;

    @ApiModelProperty("全款付款日期")
    private Date payDate;

    @ApiModelProperty("首付金额")
    private Long downPayment;

    @ApiModelProperty("贷款金额")
    private Long loanMoney;

    @ApiModelProperty("贷款年限")
    private Integer loanYearNum;

    @ApiModelProperty(" 贷款类型 0-组合贷款 1-商业贷款 2-公积金贷款")
    private Integer loanType;

    @ApiModelProperty("商业贷款银行")
    private String businessLoanBank;

    @ApiModelProperty("商业贷款金额")
    private Long businessLoanMoney;

    @ApiModelProperty("公积金类型 1-省公积金 2-市公积金")
    private Integer accumulationFundType;

    @ApiModelProperty("公积金金额")
    private Long accumulationFundMoney;

    @ApiModelProperty("分期金额")
    private List<Long> payStages;

    @ApiModelProperty(value = "合并行数",notes = "买受人和分期的行数比较值，取大值")
    private List<Integer> mergeRows;

}
