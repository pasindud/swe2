/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.repository;

import com.app.enties.Merchant;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/** @author Pasindu */
public interface MerchantRepository extends CrudRepository<Merchant, Long> {
  List<Merchant> findAll();
}
