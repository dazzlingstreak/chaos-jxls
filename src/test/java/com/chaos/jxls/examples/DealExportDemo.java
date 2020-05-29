package com.chaos.jxls.examples;

import com.alibaba.fastjson.JSON;
import com.chaos.jxls.utils.JxlsUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangdawei on 2020/5/29.
 */
@SpringBootTest
public class DealExportDemo {

    private static final Logger logger = LoggerFactory.getLogger(DealExportDemo.class);
    private static final String template = "DealExport.xlsx";
    private static final String output = "target/export.xlsx";

    @Test
    public void test() throws IOException {

        //prepare data
        String json = "[{\"accumulationFundMoney\":0,\"accumulationFundType\":0,\"address\":\"fortest\",\"auditStatus\":2,\"businessLoanBank\":\"\",\"businessLoanMoney\":0,\"contractNo\":\"\",\"customerName\":\"尔康\",\"dealDate\":1590422400000,\"dealId\":24927,\"dealMoney\":null,\"discountRemark\":\"\",\"discountType\":1,\"downPayment\":0,\"index\":1,\"loanMoney\":0,\"loanType\":0,\"loanYearNum\":0,\"otherBuyerList\":[{\"buyersName\":\"变更编辑测\",\"createTime\":1453098588000,\"gender\":1,\"idNumber\":\"484248548545450055\",\"otherBuyersId\":21983,\"phone\":\"15349086956\",\"relationship\":\"\",\"type\":2},{\"buyersName\":\"提了\",\"createTime\":1453098588000,\"gender\":2,\"idNumber\":\"185181594646466111\",\"otherBuyersId\":22002,\"phone\":\"154824545555\",\"relationship\":\"\",\"type\":2},{\"buyersName\":\"我们\",\"createTime\":1453098588000,\"gender\":2,\"idNumber\":\"845496464646645111\",\"otherBuyersId\":22003,\"phone\":\"158154545411\",\"relationship\":\"\",\"type\":2}],\"otherBuyersIds\":[22003,21983,22002],\"payDate\":1590422400000,\"payType\":3,\"payment\":5000000000,\"phone\":\"+8615789664499\",\"reductionValue\":0,\"roomFullName\":\"321501\",\"unitPrice\":250000000},{\"accumulationFundMoney\":500000000,\"accumulationFundType\":2,\"address\":\"fortest\",\"auditStatus\":2,\"businessLoanBank\":\"\",\"businessLoanMoney\":0,\"contractNo\":\"\",\"customerName\":\"一个\",\"dealDate\":1588608000000,\"dealId\":24925,\"dealMoney\":15000000000,\"discountRemark\":\"\",\"discountType\":4,\"downPayment\":10000000000,\"index\":2,\"loanMoney\":5000000000,\"loanType\":2,\"loanYearNum\":30,\"otherBuyerList\":[{\"buyersName\":\"一个\",\"createTime\":1588832811000,\"gender\":1,\"idNumber\":\"\",\"otherBuyersId\":29550,\"phone\":\"15566665555\",\"relationship\":\"\",\"type\":3}],\"otherBuyersIds\":[29550],\"payType\":1,\"payment\":0,\"phone\":\"+8615566665555\",\"reductionValue\":0,\"roomFullName\":\"311903\",\"unitPrice\":150000}]";
        List<DealExportDTO> exportItems = JSON.parseArray(json, DealExportDTO.class);

        Map<String, Object> model = new HashMap<>();
        model.put("items", exportItems);

        try (InputStream is = DealExportDemo.class.getClassLoader().getResourceAsStream(template)) {
            try (OutputStream os = new FileOutputStream(output)) {

                JxlsUtils.exportExcel(is,os,model);
            }
        }

    }


}
