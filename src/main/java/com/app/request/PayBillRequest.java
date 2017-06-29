

package com.app.request;

import com.app.annotation.Checkdb;
import com.app.enties.MerchantServices;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/** @author Pasindu */
public class PayBillRequest {
  @NotNull(message = "Incorrect bill reference number.")
  private String billReferenceNumber;

  @Min(value = 1, message = "Incorrect amount.")
  private float amount = -1;

  @Checkdb(message = "Invalid service selected", entityClass = MerchantServices.class)
  @NotNull(message = "Service id has not been selected.")
  private Integer selectedServiceId;

  @Checkdb(message = "Invalid account selected", userCheck = true)
  @NotNull(message = "Account has not been selected.")
  private Integer selectedAccountId;

  public int getSelectedAccountId() {
    return selectedAccountId;
  }

  public void setSelectedAccountId(int selectedAccountId) {
    this.selectedAccountId = selectedAccountId;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public String getBillReferenceNumber() {
    return billReferenceNumber;
  }

  public void setBillReferenceNumber(String billReferenceNumber) {
    this.billReferenceNumber = billReferenceNumber;
  }

  public int getSelectedServiceId() {
    return selectedServiceId;
  }

  public void setSelectedServiceId(int selectedServiceId) {
    this.selectedServiceId = selectedServiceId;
  }
}
