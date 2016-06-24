/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Eddy
 */
@Entity
public class BillPrefixTable implements Serializable {

//    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "BILL_PREFIX_ID_PK", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long billPrefixIdPk;

    @Column(name = "BILL_PREFIX", length = 20, unique = true, nullable = false)
    private String billPrefix;

    @Column(name = "OUR_DESCRIPTION", length = 40, unique = false, nullable = true)
    private String ourDescription;

    @Column(name = "BILL_NAME", length = 50, unique = false, nullable = true)
    private String billName;

    @Column(name = "BILL_PHONE1", length = 20, unique = false, nullable = true)
    private long billPhone1;

    @Column(name = "BILL_PHONE2", length = 20, unique = false, nullable = true)
    private long billPhone2;

    @Column(name = "BILL_ADDR", length = 100, unique = false, nullable = true)
    private String billAddress;

    @Column(name = "BILL_EMAIL", length = 30, unique = false, nullable = true)
    private String email;

    @Column(name = "BILL_TIN", length = 20, unique = false, nullable = true)
    private String billTin;

    @Column(name = "BILL_SERVICE_NO", length = 20, unique = false, nullable = true)
    private String billServiceNo;

    @Column(name = "BILL_BANK_ACC_1", length = 20, unique = false, nullable = true)
    private String bankAccount1;

    @Column(name = "BILL_IFSC_BANK_ACC_1", length = 20, unique = false, nullable = true)
    private String ifscBankAccount1;

    @Column(name = "BILL_BANK_ACC_2", length = 20, unique = false, nullable = true)
    private String bankAccount2;

    @Column(name = "BILL_IFSC_BANK_ACC_2", length = 20, unique = false, nullable = true)
    private String ifscBankAccount2;

    @OneToMany(mappedBy = "billPrefixTableFk", cascade = CascadeType.ALL, fetch = FetchType.LAZY/*, orphanRemoval = true*/)
//    @JoinColumn(name = "PRODUCT_ID_FK")
    private List<BillSale> billTableSalelist;

    @OneToMany(mappedBy = "billPrefixTableFk", cascade = CascadeType.ALL, fetch = FetchType.LAZY/*, orphanRemoval = true*/)
//    @JoinColumn(name = "PRODUCT_ID_FK")
    private List<BillPurchase> billTablePurchaselist;

    @OneToMany(mappedBy = "billPrefixTableFk", cascade = CascadeType.ALL, fetch = FetchType.LAZY/*, orphanRemoval = true*/)
//    @JoinColumn(name = "PRODUCT_ID_FK")
    private List<ChallanSale> challanTableSalelist;

    @OneToMany(mappedBy = "billPrefixTableFk", cascade = CascadeType.ALL, fetch = FetchType.LAZY/*, orphanRemoval = true*/)
//    @JoinColumn(name = "PRODUCT_ID_FK")
    private List<OrderPurchase> challanTablePurchaselist;

    public long getBillPrefixIdPk() {
        return billPrefixIdPk;
    }

    public void setBillPrefixIdPk(long billPrefixIdPk) {
        this.billPrefixIdPk = billPrefixIdPk;
    }

    public String getBillPrefix() {
        return billPrefix;
    }

    public void setBillPrefix(String billPrefix) {
        this.billPrefix = billPrefix;
    }

    public String getOurDescription() {
        return ourDescription;
    }

    public void setOurDescription(String ourDescription) {
        this.ourDescription = ourDescription;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public long getBillPhone1() {
        return billPhone1;
    }

    public void setBillPhone1(long billPhone1) {
        this.billPhone1 = billPhone1;
    }

    public long getBillPhone2() {
        return billPhone2;
    }

    public void setBillPhone2(long billPhone2) {
        this.billPhone2 = billPhone2;
    }

    public String getBillAddress() {
        return billAddress;
    }

    public void setBillAddress(String billAddress) {
        this.billAddress = billAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBillTin() {
        return billTin;
    }

    public void setBillTin(String billTin) {
        this.billTin = billTin;
    }

    public String getBillServiceNo() {
        return billServiceNo;
    }

    public void setBillServiceNo(String billServiceNo) {
        this.billServiceNo = billServiceNo;
    }

    public String getBankAccount1() {
        return bankAccount1;
    }

    public void setBankAccount1(String bankAccount1) {
        this.bankAccount1 = bankAccount1;
    }

    public String getIfscBankAccount1() {
        return ifscBankAccount1;
    }

    public void setIfscBankAccount1(String ifscBankAccount1) {
        this.ifscBankAccount1 = ifscBankAccount1;
    }

    public String getBankAccount2() {
        return bankAccount2;
    }

    public void setBankAccount2(String bankAccount2) {
        this.bankAccount2 = bankAccount2;
    }

    public String getIfscBankAccount2() {
        return ifscBankAccount2;
    }

    public void setIfscBankAccount2(String ifscBankAccount2) {
        this.ifscBankAccount2 = ifscBankAccount2;
    }

    public List<BillSale> getBillTableSalelist() {
        return billTableSalelist;
    }

    public void setBillTableSalelist(List<BillSale> billTableSalelist) {
        this.billTableSalelist = billTableSalelist;
    }

    public List<BillPurchase> getBillTablePurchaselist() {
        return billTablePurchaselist;
    }

    public void setBillTablePurchaselist(List<BillPurchase> billTablePurchaselist) {
        this.billTablePurchaselist = billTablePurchaselist;
    }

    public List<ChallanSale> getChallanTableSalelist() {
        return challanTableSalelist;
    }

    public void setChallanTableSalelist(List<ChallanSale> challanTableSalelist) {
        this.challanTableSalelist = challanTableSalelist;
    }

    public List<OrderPurchase> getChallanTablePurchaselist() {
        return challanTablePurchaselist;
    }

    public void setChallanTablePurchaselist(List<OrderPurchase> challanTablePurchaselist) {
        this.challanTablePurchaselist = challanTablePurchaselist;
    }

}
