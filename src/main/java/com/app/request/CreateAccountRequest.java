package com.app.request;

import com.app.enties.AccountType;
import com.app.enties.Branch;
import com.app.enties.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by Pasindu on 6/29/17.
 */
public class CreateAccountRequest {
    private String currency;
    private int accTypeid;
    private int branchid;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAccTypeid() {
        return accTypeid;
    }

    public void setAccTypeid(int accTypeid) {
        this.accTypeid = accTypeid;
    }

    public int getBranchid() {
        return branchid;
    }

    public void setBranchid(int branchid) {
        this.branchid = branchid;
    }
}
