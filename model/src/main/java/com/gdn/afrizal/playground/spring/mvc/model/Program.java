package com.gdn.afrizal.playground.spring.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "program")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Program {
    private Long id;
    private String name;
    private GradeType grade;
}
