

package com.app.repository;

import com.app.enties.Merchant;
import com.app.enties.MerchantServices;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/** @author Pasindu */
public interface MerchantServicesRepository extends CrudRepository<MerchantServices, Long> {

    public MerchantServices findByServiceid(int selectedServiceId);
  // List<MerchantServices> findAllByMerchantUserid(Merchant merchant);
}
