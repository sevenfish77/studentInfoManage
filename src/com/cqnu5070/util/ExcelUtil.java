package com.cqnu5070.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
/**
 * 导出excel文件的工具类
 * @author Administrator
 *
 * @param <T>
 */
public class ExcelUtil<T> {
	
	public void exportExcel(String[] headers, List<T> list, OutputStream out) 
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        // 生成一个样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        // 设置这些样式
        headerStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        headerStyle.setFont(font);

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
            //HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(headers[i]);
        }
        // 遍历集合数据，产生数据行
        int index = 0;
        for(T t : list) {
            index++;
            row = sheet.createRow(index);
            for (int i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell(i);
                String value = BeanUtils.getProperty(t, headers[i]);
                cell.setCellValue(value);
            }
        }
        //输出
        try {
        	workbook.write(out);
    		out.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }

    }
	
	public void exportMapExcel(String[] headers, List<Map<String, Object>> list, OutputStream out) 
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        // 生成一个样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
            //HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(headers[i]);
        }
        // 遍历集合数据，产生数据行
        int index = 0;
        for(Map<String, Object> map : list) {
            index++;
            row = sheet.createRow(index);
            
            Set<Entry<String, Object>> set = map.entrySet();
            
            Iterator<Entry<String, Object>> it = set.iterator();
            int i = 0;
            while(it.hasNext()){
            	
            	Entry<String, Object> next = it.next();
            	Object value = next.getValue();
            	String key = next.getKey();
            	if(!key.contains("escoreid")){
            		HSSFCell cell = row.createCell(i++);
                	if(value instanceof String){
                		String str = (String) value;
                		cell.setCellValue(str);
                	} else{
                		Integer score = (Integer) value;
                		cell.setCellValue(score.intValue());
                	}
            	}
            }
        }
        //输出
        try {
        	workbook.write(out);
    		out.close();
        } catch (IOException e) {
        	e.printStackTrace();
        }

    }
	
	
}
