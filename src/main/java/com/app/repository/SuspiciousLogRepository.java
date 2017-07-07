package com.app.repository;

import com.app.enties.SecurityQuestions;
import com.app.enties.SuspiciousLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Pasindu on 7/8/17.
 */
public interface SuspiciousLogRepository extends JpaRepository<SuspiciousLog, Integer> {
}
