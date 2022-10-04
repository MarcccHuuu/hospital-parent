package com.hyc.clinicsystem.hosp.controller;

import com.hyc.clinicsystem.hosp.service.HospitalSetService;
import com.hyc.clinicsystem.model.hosp.HospitalSet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    // 查询医院表所有信息
    // http://localhost:8201/admin/hosp/hospitalSet/findAll
    @ApiOperation(value = "获取所有医院设置")
    @GetMapping("findAll")
    public List<HospitalSet> findAllHospitalSet() {
        return hospitalSetService.list();
    }

    // 逻辑删除医院设置
    @ApiOperation(value = "逻辑删除医院设置")
    @DeleteMapping("{id}")
    public boolean removeHospitalSet(@PathVariable Long id) {
        return hospitalSetService.removeById(id);
    }

}
