package com.gdn.afrizal.playground.spring.data.repository;

import com.gdn.afrizal.playground.spring.model.StudentAccount;

public interface StudentAccountCustomRepository {
  StudentAccount findByUsername(String username);
  StudentAccount findByUsernameAndPassword(String username, String password);
}
