package com.echeng.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echeng.yygh.common.utils.MD5;
import com.echeng.yygh.hosp.service.HospitalSerServer;
import com.echeng.yygh.model.hosp.HospitalSet;
import com.echeng.yygh.vo.hosp.HospitalQueryVo;
import com.echeng.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.echeng.yygh.common.result.Result;

import java.util.List;
import java.util.Random;

/**
 * @author ${用户}
 * @version 1.0
 * @description: 待办事项
 * @date 2022/1/28 4:39 下午
 * localhost:8201.doc.html 查看接口Api
 */
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
@Api(value = "医院接口", tags = "医院设置接口")
public class HospitalSetController {
    //    注入service
    @Autowired
    private HospitalSerServer hospitalSetServer;

    //    查询医院设置表所有信息
    @ApiOperation(value = "查询所有医院信息")
    @GetMapping("findAll")
    public Result findAllHospitalSet() {
        //    调用service的方法
        List<HospitalSet> list = hospitalSetServer.list();
        return Result.ok(list);
    }

    //    删除医院设置
    @ApiOperation(value = "删除医院信息")
    @DeleteMapping("{id}")
    public Result removeHospSet(@PathVariable Long id) {
        boolean flag = hospitalSetServer.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //条件查询带分页
    @ApiOperation(value = "条件查询")
    @PostMapping("fingPage/{current}/{limit}")
    public Result findPageHospSet(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
        //创建page对象，传递当前值，每页记录数
        Page<HospitalSet> page = new Page<>(current, limit);
        //构建条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        String hosname = hospitalSetQueryVo.getHosname();//医院名称
        String hoscode = hospitalSetQueryVo.getHoscode();//医院编号
        if (!StringUtils.isEmpty(hoscode)) {
            wrapper.eq("hoscode", hospitalSetQueryVo.getHoscode());
        }
        if (!StringUtils.isEmpty(hosname)) {
            wrapper.like("hosname", hospitalSetQueryVo.getHosname());
        }
        Page<HospitalSet> pageHospSet = hospitalSetServer.page(page, wrapper);
        return Result.ok(pageHospSet);
    }


    //    添加医院设置
    @ApiOperation(value = "添加医院")
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        //    设置状态 1使用 0不使用
        hospitalSet.setStatus(1);

        //    签名密钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));
        //    调用service
        boolean save = hospitalSetServer.save(hospitalSet);
        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //    根据id获取医院设置
    @ApiOperation(value = "根据id查询医院")
    @GetMapping("getHospSet/{id}")
    public Result getHospSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetServer.getById(id);
        return Result.ok(hospitalSet);
    }

    //    修改医院设置
    @ApiOperation(value = "修改医院信息")
    @GetMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet) {
        boolean flag = hospitalSetServer.updateById(hospitalSet);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //    批量删除医院设置
    @ApiOperation(value = "批量删除医院")
    @DeleteMapping("batchRemove")
    public Result batchRemoveHospitalSet(@RequestBody List<Long> idList) {
        hospitalSetServer.removeByIds(idList);
        return Result.ok();
    }

    //    医院设置锁定和解锁
    @ApiOperation(value = "设置医院锁")
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalset(@PathVariable Long id,
                                  @PathVariable Integer status) {
    //    根据id查询医院设置信息
        HospitalSet hospitalSet = hospitalSetServer.getById(id);
    //    设置状态
        hospitalSet.setStatus(status);
    //    调用方法
        hospitalSetServer.updateById(hospitalSet);
        return Result.ok();
    }


    //    发送签名密钥
    @ApiOperation(value = "发送签名密钥")
    public Result lockHospitalset(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetServer.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
        //TODO 发送短信
        return Result.ok();
    }

}
