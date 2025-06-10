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
   * Global handler logs.
   */
  public static final String GLOBAL_HANDLER = "[global-exception]";


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

}
