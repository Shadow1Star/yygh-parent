package com.echeng.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author ${用户}
 * @version 1.0
 * @description: 待办事项
 * @date 2022/2/4 3:43 下午
 */
public class TestWriter {
    public static void main(String[] args) {
    //    构建数据list集合
        List<UserData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserData data = new UserData();
            data.setUid(i);
            data.setUname("lucy" + i);
            list.add(data);
        }

    //    设置excel文件路径和名称
        String fileName = "/Users/pansir/Desktop/excel/01.xlsx";
    //    调用方法实现操作
        EasyExcel.write(fileName, UserData.class).sheet("用户信息").doWrite(list);

    }
}
