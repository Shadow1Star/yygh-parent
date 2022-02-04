package com.echeng.easyexcel;

import com.alibaba.excel.EasyExcel;

/**
 * @author ${用户}
 * @version 1.0
 * @description: 待办事项
 * @date 2022/2/4 3:55 下午
 */
public class TestRead {
    public static void main(String[] args) {
    //    读取文件路径

        String fileName = "/Users/pansir/Desktop/excel/01.xlsx";

    //调用方法
        EasyExcel.read(fileName, UserData.class, new ExcelListtener()).sheet().doRead();

    }
}
