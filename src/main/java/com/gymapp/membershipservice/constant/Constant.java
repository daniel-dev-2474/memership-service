package com.gymapp.membershipservice.constant;


/**
 * Contains constant values used throughout the membership-service.
 */
public final class Constant {

  /**
   * Private constructor.
   */
  private Constant() {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * Validation message indicating that the name field is required.
   */
  public static final String NAME_REQUIRED = "The name is required";

  /**
   * Base URI for pass-related API endpoints.
   */
  public static final String API_PASS = "/api/passes";


  /**
   * Base URI for membership-related API endpoints.
   */
  public static final String API_MEMBERSHIP = "/api/memberships";

  /**
   * URI path parameter used to represent a membership ID.
   */
  public static final String PARAM_ID = "/{userId}";

  /**
   * Separator string consisting of a colon and space, used in error formatting.
   */
  public static final String COLON_SEPARATOR = ": ";

  /**
   * Separator string consisting of a comma and space,
   * commonly used to join multiple items.
   */
  public static final String COMMA_SEPARATOR = ", ";

  /**
   * General message used for failed validation errors.
   */
  public static final String FAILED_VALIDATION = "Failed Validation";

  /**
   * General message used for business rule validation failures.
   */
  public static final String BUSINESS_VALIDATION = "Business Error";
}
