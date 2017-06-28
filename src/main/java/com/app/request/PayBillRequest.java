

package com.app.request;

/**
 *
 * @author Pasindu
 */
public class PayBillRequest {
    private float amount = -1;
    private String billReferenceNumber;
    private int selectedServiceId ;
    private int selectedAccountId;

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
