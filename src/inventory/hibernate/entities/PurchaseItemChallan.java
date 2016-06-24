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
public class PurchaseItemChallan implements Serializable {

//    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "PUR_ITEM_CH_ID_PK", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long purchaseItemChalllanIdPk;

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
    private OrderPurchase orderPurchaseFk;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID_FK")
    private ItemTable itemTableFk;

    @OneToMany(mappedBy = "purchaseItemChallanFk", cascade = CascadeType.ALL/*, fetch = FetchType.LAZY/*, orphanRemoval = false,*/)
//    @JoinColumn(name = "RETURN_PUR_ID_FK")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<ReturnPurchaseChallanItem> returnPurchaseChallanList;

    public long getPurchaseItemChalllanIdPk() {
        return purchaseItemChalllanIdPk;
    }

    public void setPurchaseItemChalllanIdPk(long purchaseItemChalllanIdPk) {
        this.purchaseItemChalllanIdPk = purchaseItemChalllanIdPk;
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

    public OrderPurchase getOrderPurchaseFk() {
        return orderPurchaseFk;
    }

    public void setOrderPurchaseFk(OrderPurchase orderPurchaseFk) {
        this.orderPurchaseFk = orderPurchaseFk;
    }

    public ItemTable getItemTableFk() {
        return itemTableFk;
    }

    public void setItemTableFk(ItemTable itemTableFk) {
        this.itemTableFk = itemTableFk;
    }

    public Set<ReturnPurchaseChallanItem> getReturnPurchaseChallanList() {
        return returnPurchaseChallanList;
    }

    public void setReturnPurchaseChallanList(Set<ReturnPurchaseChallanItem> returnPurchaseChallanList) {
        this.returnPurchaseChallanList = returnPurchaseChallanList;
    }

}
