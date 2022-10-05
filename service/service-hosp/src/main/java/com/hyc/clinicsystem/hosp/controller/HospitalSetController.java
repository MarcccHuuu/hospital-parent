package com.hyc.clinicsystem.hosp.controller;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hyc.clinicsystem.common.result.Result;
import com.hyc.clinicsystem.hosp.service.HospitalSetService;
import com.hyc.clinicsystem.hosp.utils.MD5;
import com.hyc.clinicsystem.model.hosp.HospitalSet;
import com.hyc.clinicsystem.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Random;

@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    // 1 查询医院表所有信息
    // http://localhost:8201/admin/hosp/hospitalSet/findAll
    @ApiOperation(value = "获取所有医院设置")
    @GetMapping("findAll")
    public Result findAllHospitalSet() {
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    // 2 逻辑删除医院设置
    @ApiOperation(value = "逻辑删除医院设置")
    @DeleteMapping("{id}")
    public Result removeHospitalSet(@PathVariable Long id) {
        boolean flag = hospitalSetService.removeById(id);
        return flag ? Result.ok() : Result.fail();
    }

    // 3 条件查询 + 分页
    @PostMapping("findPageHospSet/{current}/{limit}")
    public Result findPageHspSet(@PathVariable long current,
                                 @PathVariable long limit,
                                 @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
        // 创建page对象，传递当前页，每页记录数
        Page<HospitalSet> page = new Page<>(current, limit);
        // 构造条件
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        String hosName = hospitalSetQueryVo.getHosname();
        String hosCode = hospitalSetQueryVo.getHoscode();
        if (StringUtils.isEmpty(hosName)) {
            wrapper.like("hosname",hosName);
        }
        if (StringUtils.isEmpty(hosCode)) {
            wrapper.eq("hoscode", hosCode);
        }
        // 调用方法实现分页查询
        Page<HospitalSet> pageHospitalSet = hospitalSetService.page(page, wrapper);
        return Result.ok(pageHospitalSet);
    }

    // 4 添加医院设置
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        // 设置状态 1 可使用 0 不能使用
        hospitalSet.setStatus(1);
        // 签名密钥
        Random random = new Random();
        hospitalSet.setSignKey(
                MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));

        // 调用service
        boolean saved = hospitalSetService.save(hospitalSet);
        return saved ? Result.ok() : Result.fail();
    }
}
