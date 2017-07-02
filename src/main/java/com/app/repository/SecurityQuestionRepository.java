/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.repository;
import com.app.enties.SecurityQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 *
 * @author dilsh
 */
public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestions, Integer> {
    SecurityQuestions findById(int id);
}
