package com.gdn.afrizal.playground.spring.dto;

import com.gdn.afrizal.playground.spring.dto.validation.ValidEmail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegistration {
  private String username;
  @ValidEmail private String email;
  private String password;
  private Long nationalId;
  private Long facultyId;
  private Long programId;
}
