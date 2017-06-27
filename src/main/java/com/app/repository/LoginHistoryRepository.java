

package com.app.repository;

import com.app.enties.LoginHistory;
import com.app.enties.Merchant;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Pasindu
 */

public interface LoginHistoryRepository extends CrudRepository<LoginHistory, Long> {

}
