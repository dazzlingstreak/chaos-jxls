package com.chaos.jxls.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * JXLS自定义方法类
 */
public class JxlsFunctions {

    private static final Logger logger = LoggerFactory.getLogger(JxlsFunctions.class);

    private static final String EMPTY_STRING = "";

    /**
     * 日期格式化方法
     *
     * @param date
     * @param format
     * @return
     */
    public String dateFormat(Date date, String format) {
        LocalDateTime minLocalDateTime = LocalDate.of(1900, 1, 1).atStartOfDay();
        Date minDate = Date.from(minLocalDateTime.toInstant(ZoneOffset.UTC));

        if (Objects.isNull(date) || date.before(minDate)) {
            return EMPTY_STRING;
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDateTime localDateTime = DateUtils.dateToLocalDateTime(date);
            return localDateTime.format(formatter);
        } catch (Exception e) {
            logger.warn("JxlsFunctions.dateFormat error,e {}", e);
        }
        return EMPTY_STRING;
    }

    /**
     * 数字格式化
     *
     * @param value       数值,支持Long Integer
     * @param divideValue 需要除以的倍数，数据库字段的值可能是原值乘以 一个倍数（如10000）存储的，保证精度。
     * @param format      格式 ，譬如："###,###.##"
     * @param unit        单位
     * @return
     */
    public String digitalProcessFormat(Object value, int divideValue, String format, String unit) {
        if (Objects.isNull(value)) {
            return EMPTY_STRING;
        }
        BigDecimal decimalValue = null;
        if (value instanceof Long) {
            decimalValue = new BigDecimal((Long) value);
        } else if (value instanceof Integer) {
            decimalValue = new BigDecimal((Integer) value);
        } else {
            return EMPTY_STRING;
        }

        double doubleValue = decimalValue.divide(new BigDecimal(divideValue), 2, RoundingMode.HALF_UP).doubleValue();
        DecimalFormat df = new DecimalFormat(format);
        return df.format(doubleValue) + unit;
    }

    /**
     * 将excel中枚举值转换为相应的文案输出
     *
     * @param key      枚举值
     * @param enumJson 枚举值对应的文案,如: [0:\"无\",1:\"省公积金\",2:\"市公积金\"]
     * @return
     */
    public String getEnumValue(Object key, String enumJson) {
        if (Objects.isNull(key)) {
            return EMPTY_STRING;
        }

        if (StringUtils.isNotBlank(enumJson)) {
            enumJson = enumJson.replace("[", "{").replace("]", "}");
            Map<String, String> enumMap = (Map<String, String>) (JSON.parse(enumJson));

            String searchEnumKey = key.toString();

            if (enumMap.containsKey(searchEnumKey)) {
                return enumMap.get(searchEnumKey);
            }
        }
        return EMPTY_STRING;
    }
}
