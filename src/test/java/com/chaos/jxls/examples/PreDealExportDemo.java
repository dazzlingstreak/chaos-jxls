package com.chaos.jxls.examples;

import com.alibaba.fastjson.JSON;
import com.chaos.jxls.utils.FileUtils;
import com.chaos.jxls.utils.JxlsUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangdawei on 2020/6/30.
 */
public class PreDealExportDemo {

    private static final Logger logger = LoggerFactory.getLogger(DealExportDemo.class);
    private static final String template = "preDealExport.xlsx";
    private static final String output = "target/preDealExport.xlsx";
    private static final String data = "PreDealExportData.json";

    @Test
    public void test() throws IOException {

        List<PreDealExportDTO> exportItems;

        //prepare data
        try (InputStream dataInputStream = DealExportDemo.class.getClassLoader().getResourceAsStream(data)) {
            byte[] bytes = FileUtils.readInputStream(dataInputStream);
            String dataJsonString = new String(bytes);
            exportItems = JSON.parseArray(dataJsonString, PreDealExportDTO.class);
        }

        Map<String, Object> model = new HashMap<>();
        model.put("items", exportItems);

        try (InputStream is = DealExportDemo.class.getClassLoader().getResourceAsStream(template)) {
            try (OutputStream os = new FileOutputStream(output)) {

                JxlsUtils.exportExcel(is,os,model);
            }
        }

    }
}
