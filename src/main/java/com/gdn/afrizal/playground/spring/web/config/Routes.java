package com.gdn.afrizal.playground.spring.web.config;

public class Routes {
  public static final String ENROLLMENT_OF_STUDENT = "/enrollment/{studentId}";
  public static final String ENROLLMENT_OF_STUDENT_COURSE = "/enrollment/{studentId}/{courseId}";
  public static final String STUDENT = "/student/{studentId}";
  public static final String REGISTER_STUDENT = "/student/register";
  public static final String PROGRAM_COURSES = "/course/program/{programId}";
  public static final String ENROLL_STUDENTS = "/course/{courseId}/enroll";

  private Routes() {}
}
