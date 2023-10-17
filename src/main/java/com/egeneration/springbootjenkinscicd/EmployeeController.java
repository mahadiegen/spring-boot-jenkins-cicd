package com.egeneration.springbootjenkinscicd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;

@RestController
public class EmployeeController {


  @RequestMapping(value = "/getEmployees", method = RequestMethod.GET)
  public ModelAndView getEmployeeInfo() {
    return new ModelAndView("getEmployees");
  }

  @RequestMapping(value = "/authorized", method = RequestMethod.GET)
  public ModelAndView authorized(@RequestParam("code") String code) throws JsonProcessingException, IOException {
    System.out.println("Authorization Code------" + code);

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    body.add("grant_type", "authorization_code");
    body.add("code", code);
    body.add("redirect_uri", "http://127.0.0.1:8088/authorized");
    body.add("client_id", "client");
    body.add("client_secret", "secret");

    HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

    ResponseEntity<String> response = restTemplate.exchange(
        "http://localhost:8000/oauth2/token",
        HttpMethod.POST,
        entity,
        String.class);

    ObjectMapper mapper = new ObjectMapper();
    JsonNode node = mapper.readTree(response.getBody());
    String token = node.path("access_token").asText();


    System.out.println(response);
    System.out.println(token);

    // Use the access token for authentication
//    HttpHeaders headers1 = new HttpHeaders();
//    headers1.add("Authorization", "Bearer " + token);
//    HttpEntity<String> entity = new HttpEntity<>(headers1);
//
//    ResponseEntity<Employee[]> employees = restTemplate.exchange(url, HttpMethod.GET, entity, Employee[].class);
//    System.out.println(employees);
//    Employee[] employeeArray = employees.getBody();
//
//    ModelAndView model = new ModelAndView("showEmployees");
//    model.addObject("employees", Arrays.asList(employeeArray));
    return new ModelAndView();
  }
}
