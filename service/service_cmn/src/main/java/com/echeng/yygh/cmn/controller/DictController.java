package com.echeng.yygh.cmn.controller;

import com.echeng.yygh.cmn.service.DictService;
import com.echeng.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.echeng.yygh.common.result.Result;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ${用户}
 * @version 1.0
 * @description: 待办事项
 * @date 2022/2/4 2:32 下午
 */
@RestController
@RequestMapping("admin/cmn/dict")
@Api(value = "数据字典", tags = "数据字典管理")
@ApiOperation(value = "数据字典管理")
public class DictController {
    @Autowired
    private DictService dictService;

    //根据数据id查询子数据列表
    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("findChindData/{id}")
    public Result findChindData(@PathVariable Long id) {
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }

    //    导出数据字典接口
    @GetMapping("exportData")
    public Result exportData(HttpServletResponse response) {
        dictService.exportDictData(response);
        return Result.ok();
    }
}
