package com.chaos.jxls.utils;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.builder.xml.XmlAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.util.TransformerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JxlsUtils {

    private static final Logger logger = LoggerFactory.getLogger(JxlsUtils.class);

    /**
     * 基于excel模板、导出配置文件xml、导出数据到指定文件
     *
     * @param templateXlsPath 模板路径
     * @param configPath      配置文件路径
     * @param outputFile      输出文件
     * @param model           数据模型
     * @throws IOException
     */
    public static void exportExcel(String templateXlsPath, String configPath, File outputFile, Map<String, Object> model) throws IOException {
        try (InputStream templateInputStream = JxlsUtils.class.getClassLoader().getResourceAsStream(templateXlsPath);
             InputStream configInputStream = JxlsUtils.class.getClassLoader().getResourceAsStream(configPath);
             FileOutputStream outputStream = new FileOutputStream(outputFile)) {

            exportExcel(templateInputStream, configInputStream, outputStream, model);
        }
    }


    /**
     * 基于excel模板+导出配置文件xml、导出数据到指定文件
     *
     * @param templateInputStream 模板文件流
     * @param configInputStream   配置文件流
     * @param os                  输出文件流
     * @param model               数据模型
     * @throws IOException
     */
    public static void exportExcel(InputStream templateInputStream, InputStream configInputStream, OutputStream os, Map<String, Object> model) throws IOException {
        Transformer transformer = TransformerFactory.createTransformer(templateInputStream, os);
        //得到jexl表达式计算类，用以扩展，增加自定义方法类，便于excel中使用
        JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) transformer.getTransformationConfig().getExpressionEvaluator();
        Map<String, Object> functionMap = new HashMap<>();
        functionMap.put("utils", new JxlsFunctions());
        JexlEngine customJexlEngine = new JexlBuilder().namespaces(functionMap).create();
        evaluator.setJexlEngine(customJexlEngine);

        XmlAreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
        List<Area> xlsAreaList = areaBuilder.build();
        Area xlsArea = xlsAreaList.get(0);

        Context context = new Context(model);
        xlsArea.applyAt(new CellRef("Sheet1!A1"), context);
        xlsArea.processFormulas();
        xlsArea.reset();
        transformer.write();
    }

    /**
     * 只基于excel模板、导出数据到指定文件
     *
     * @param templatePath 模板路径
     * @param file         输出文件
     * @param model        数据模型
     * @throws IOException
     */
    public static void exportExcel(String templatePath, File file, Map<String, Object> model) throws IOException {
        try (InputStream is = JxlsUtils.class.getClassLoader().getResourceAsStream(templatePath);
             FileOutputStream os = new FileOutputStream(file)) {
            exportExcel(is, os, model);
        }
    }

    /**
     * 只基于excel模板、导出数据到指定文件
     *
     * @param templateInputStream 模板路径文件流
     * @param os                  输出文件流
     * @param model               数据模型
     * @throws IOException
     */
    public static void exportExcel(InputStream templateInputStream, OutputStream os, Map<String, Object> model) throws IOException {
        Transformer transformer = TransformerFactory.createTransformer(templateInputStream, os);
        JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator) transformer.getTransformationConfig().getExpressionEvaluator();
        //添加自定义函数
        Map<String, Object> functionMap = new HashMap<>();
        functionMap.put("utils", new JxlsFunctions());
        JexlEngine customJexlEngine = new JexlBuilder().namespaces(functionMap).create();
        evaluator.setJexlEngine(customJexlEngine);

        AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
        XlsCommentAreaBuilder.addCommandMapping("merge", MergeCommand.class);
        List<Area> xlsAreaList = areaBuilder.build();
        Area xlsArea = xlsAreaList.get(0);

        Context context = new Context(model);
        xlsArea.applyAt(new CellRef("Sheet1!A1"), context);
        xlsArea.processFormulas();
        xlsArea.reset();
        transformer.write();
    }
}
