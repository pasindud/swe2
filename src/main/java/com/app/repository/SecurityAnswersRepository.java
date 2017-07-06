package com.app.repository;

import com.app.enties.Branch;
import com.app.enties.SecurityAnswers;
import com.app.enties.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SecurityAnswersRepository extends CrudRepository<SecurityAnswers, Integer> {
  List<SecurityAnswers> findByUserId(int users);
}