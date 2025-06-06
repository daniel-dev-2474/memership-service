package com.gymapp.membershipservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class of the application.
 */
@SpringBootApplication
public final class MembershipServiceApplication {

  /**
   * Private constructor of the class.
   */
  private MembershipServiceApplication() {
  }

  /**
   * Main method.
   * @param args arguments.
   */
  public static void main(final String[] args) {
    SpringApplication.run(MembershipServiceApplication.class, args);
  }

}
