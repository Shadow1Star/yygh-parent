package com.echeng.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author ${用户}
 * @version 1.0
 * @description: 待办事项
 * @date 2022/2/4 3:50 下午
 */
public class ExcelListtener extends AnalysisEventListener<UserData> {
    @Override
    //从第二行开始，一行一行读取数据
    public void invoke(UserData userData, AnalysisContext analysisContext) {
        System.out.println(userData);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息" + headMap);
    }

    @Override
    //读取之后执行
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
