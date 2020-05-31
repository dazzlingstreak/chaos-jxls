package com.chaos.jxls.examples;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.chaos.jxls.utils.FileUtils;
import com.chaos.jxls.utils.JxlsUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
    private static final String data = "DealExportData.json";

    @Test
    public void test() throws IOException {

        List<DealExportDTO> exportItems;

        //prepare data
        try (InputStream dataInputStream = DealExportDemo.class.getClassLoader().getResourceAsStream(data)) {
            byte[] bytes = FileUtils.readInputStream(dataInputStream);
            String dataJsonString = new String(bytes);
            exportItems = JSON.parseArray(dataJsonString, DealExportDTO.class);
        }

        Map<String, Object> model = new HashMap<>();
        model.put("items", exportItems);

        try (InputStream is = DealExportDemo.class.getClassLoader().getResourceAsStream(template)) {
            try (OutputStream os = new FileOutputStream(output)) {

                JxlsUtils.exportExcel(is, os, model);
            }
        }
        System.out.println("--end--");

    }


}
