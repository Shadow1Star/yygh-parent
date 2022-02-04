package com.echeng.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author ${用户}
 * @version 1.0
 * @description: 待办事项
 * @date 2022/2/4 3:40 下午
 */
@Data
public class UserData {
    @ExcelProperty("用户编号")
    private int uid;
    @ExcelProperty("用户名称")
    private String uname;
}
