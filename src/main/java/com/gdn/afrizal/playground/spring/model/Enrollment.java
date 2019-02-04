package com.gdn.afrizal.playground.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    private Long id;
    private Long studentId;
    private String courseId;
    private Date enrollmentDate;
    private Boolean active;
}
