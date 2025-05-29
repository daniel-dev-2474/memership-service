package com.gymapp.membershipservice.constant;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Constant {


  public static final String NAME_REQUIRED = "The name is required";
  public static final String PASSES_REQUIRED = "At least 1 pass must be included";
  public static final String MEMBERSHIP_NOT_FOUND = "Membership not found";
  public static final String API_MEMBERSHIP = "/api/membership/";
  public static final String PARAM_ID = "/{id}";
  
  public static final String COLON_SEPARATOR = ": ";
  public static final CharSequence COMMA_SEPARATOR = ", ";
  public static final String FAILED_VALIDATION = "Validacion fallida";
  public static final String BUSINESS_VALIDATION = "Error de negocio";
}
