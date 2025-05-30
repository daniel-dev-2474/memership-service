package com.gymapp.membershipservice.constant;

import lombok.NoArgsConstructor;

/**
 * Contains constant values used throughout the membership-service.
 */
@NoArgsConstructor
public class Constant {

  /**
   * Validation message indicating that the name field is required.
   */
  public static final String NAME_REQUIRED = "The name is required";

  /**
   * Validation message indicating that at least one pass must be provided.
   */
  public static final String PASSES_REQUIRED = "At least 1 pass must be included";

  /**
   * Message used when a membership record is not found.
   */
  public static final String MEMBERSHIP_NOT_FOUND = "Membership not found";

  /**
   * Base URI for membership-related API endpoints.
   */
  public static final String API_MEMBERSHIP = "/api/membership";

  /**
   * URI path parameter used to represent a membership ID.
   */
  public static final String PARAM_ID = "/{id}";

  /**
   * Separator string consisting of a colon and space, used in error formatting.
   */
  public static final String COLON_SEPARATOR = ": ";

  /**
   * Separator string consisting of a comma and space, commonly used to join multiple items.
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
