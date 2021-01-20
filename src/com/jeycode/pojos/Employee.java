package com.jeycode.pojos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Employee
{

      private String name,surname,category;
      private int age,employeeCode;
      private float salary;

}
