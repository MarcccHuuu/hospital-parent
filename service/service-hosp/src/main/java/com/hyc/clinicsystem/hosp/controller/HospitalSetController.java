package com.hyc.clinicsystem.hosp.controller;

import com.hyc.clinicsystem.hosp.service.HospitalSetService;
import com.hyc.clinicsystem.model.hosp.HospitalSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    // 查询医院表所有信息
    // http://localhost:8201/admin/hosp/hospitalSet/findAll
    @GetMapping("findAll")
    public List<HospitalSet> findAllHospitalSet() {
        return hospitalSetService.list();
    }

}
