package com.egeneration.springbootjenkinscicd;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class EmployeeController {


  @GetMapping("/hello")
  public String getMessage() {
    return "Salam to All from GitHub";
  }

}
