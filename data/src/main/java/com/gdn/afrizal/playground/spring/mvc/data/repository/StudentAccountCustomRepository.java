package com.gdn.afrizal.playground.spring.mvc.data.repository;

import com.gdn.afrizal.playground.spring.mvc.model.StudentAccount;

public interface StudentAccountCustomRepository {
  StudentAccount findByUsername(String username);
  StudentAccount findByUsernameAndPassword(String username, String password);
}
