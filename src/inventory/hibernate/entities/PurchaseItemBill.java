/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Eddy
 */
@Entity
public class PurchaseItemBill implements Serializable {

//    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "PUR_ITEM_BILL_ID_PK", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long purchaseItemBillIdPk;

    @Column(name = "APPLIED_PUR_RATE", unique = false, nullable = false, precision = 52, scale = 0)
    private double appliedPurchaseRate;

    @Column(name = "APPLIED_PUR_TAX", unique = false, nullable = false, precision = 52, scale = 0)
    private double appliedPurchaseTax;

    @Column(name = "APPLIED_MRP", unique = false, nullable = false, precision = 52, scale = 0)
    private double appliedPurchaseMRP;

    @Column(name = "APPLIED_VAT", unique = false, nullable = false, precision = 52, scale = 0)
    private double appliedPurchaseVAT;

    @Column(name = "OTHER_CHARGES", unique = false, nullable = false, precision = 52, scale = 0)
    private double otherCharges;

    @Column(name = "PURCHASE_QTY", unique = false, nullable = false, precision = 52, scale = 0)
    private double purchaseQty;

    @Column(name = "TOTAT_AMOUNT", unique = false, nullable = false, precision = 52, scale = 0)
    private double totalAmount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "BILL_ID_FK")
    private BillPurchase billPurchaseFk;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID_FK")
    private ItemTable itemTableFk;

    @OneToMany(mappedBy = "purchaseItemBillFk", cascade = CascadeType.ALL/*, fetch = FetchType.LAZY/*, orphanRemoval = false,*/)
//    @JoinColumn(name = "RETURN_PUR_ID_FK")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<ReturnPurchaseBillItem> returnPurchaseBillList;

    public long getPurchaseItemBillIdPk() {
        return purchaseItemBillIdPk;
    }

    public void setPurchaseItemBillIdPk(long purchaseItemBillIdPk) {
        this.purchaseItemBillIdPk = purchaseItemBillIdPk;
    }

    public double getAppliedPurchaseRate() {
        return appliedPurchaseRate;
    }

    public void setAppliedPurchaseRate(double appliedPurchaseRate) {
        this.appliedPurchaseRate = appliedPurchaseRate;
    }

    public double getAppliedPurchaseTax() {
        return appliedPurchaseTax;
    }

    public void setAppliedPurchaseTax(double appliedPurchaseTax) {
        this.appliedPurchaseTax = appliedPurchaseTax;
    }

    public double getAppliedPurchaseMRP() {
        return appliedPurchaseMRP;
    }

    public void setAppliedPurchaseMRP(double appliedPurchaseMRP) {
        this.appliedPurchaseMRP = appliedPurchaseMRP;
    }

    public double getAppliedPurchaseVAT() {
        return appliedPurchaseVAT;
    }

    public void setAppliedPurchaseVAT(double appliedPurchaseVAT) {
        this.appliedPurchaseVAT = appliedPurchaseVAT;
    }

    public double getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(double otherCharges) {
        this.otherCharges = otherCharges;
    }

    public double getPurchaseQty() {
        return purchaseQty;
    }

    public void setPurchaseQty(double purchaseQty) {
        this.purchaseQty = purchaseQty;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BillPurchase getBillPurchaseFk() {
        return billPurchaseFk;
    }

    public void setBillPurchaseFk(BillPurchase billPurchaseFk) {
        this.billPurchaseFk = billPurchaseFk;
    }

    public ItemTable getItemTableFk() {
        return itemTableFk;
    }

    public void setItemTableFk(ItemTable itemTableFk) {
        this.itemTableFk = itemTableFk;
    }

    public Set<ReturnPurchaseBillItem> getReturnPurchaseBillList() {
        return returnPurchaseBillList;
    }

    public void setReturnPurchaseBillList(Set<ReturnPurchaseBillItem> returnPurchaseBillList) {
        this.returnPurchaseBillList = returnPurchaseBillList;
    }

}
