

package com.app.repository;

import com.app.enties.MerchantServices;
import org.springframework.data.repository.CrudRepository;

/** @author Pasindu */
public interface MerchantServicesRepository extends CrudRepository<MerchantServices, Long> {

  public MerchantServices findByServiceid(int selectedServiceId);
  // List<MerchantServices> findAllByMerchantUserid(Merchant merchant);
}
