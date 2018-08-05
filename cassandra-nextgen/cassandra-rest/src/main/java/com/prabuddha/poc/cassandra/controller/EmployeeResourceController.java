package com.prabuddha.poc.cassandra.controller;

import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.UDTValue;
import com.prabuddha.poc.cassandra.model.EmployeeCW;
import com.prabuddha.poc.cassandra.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.InputStream;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeResourceController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/status/")
    @ResponseBody
    public String getServiceStatus() {
        return "Employee Service is Up and Running";
    }

    @ApiOperation(value = "Fetch Employee Details on the basis of employee id and name", response = InputStream.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrived Employee"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 204, message = "No content")
    })
    @GetMapping(value = "/getDetails/{id}/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> getEmployeeDetailsByIdAndName(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String employee_id, @PathVariable("name") String name) {
        List<Map<String, String>> listMap = new ArrayList<>();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        EmployeeCW employee = employeeService.getEmployeeDetailsByIdAndName(employee_id, name);
        Map<String, String> empMap = new HashMap<>();
        empMap.put("id", employee.getEmployee_id().toString());
        empMap.put("name", employee.getName());
        empMap.put("age", String.valueOf(employee.getAge()));
        List<UDTValue> prevEmpList = employee.getPrevEmploymentList();
        List<String> prevEmployerDetails = new ArrayList<>();
        for (UDTValue udtValue : prevEmpList) {
            String employerName = udtValue.get("name", String.class);
            String employerAddress = udtValue.get("address", String.class);
            LocalDate employerEstDate = udtValue.get("est_date", LocalDate.class);
            java.util.Date estDate = new Date(employerEstDate.getMillisSinceEpoch());
            prevEmployerDetails.add("[" + employerName + ";" + employerAddress + ";" + estDate.toString() + "]");
        }
        empMap.put("employer history", prevEmployerDetails.toString());
        List<String> skillList = new ArrayList<>();
        for (String key : employee.getSkillExp().keySet()) {
            Integer value = employee.getSkillExp().get(key);
            skillList.add(key + " = " + String.valueOf(value));
        }
        empMap.put("skill experience", skillList.toString());
        return empMap;
    }
}
