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
public class SaleItemBill implements Serializable {

//    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "SALE_ITEM_BILL_ID_PK", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long saleItemBillIdPk;

    @Column(name = "APPLIED_SALE_RATE", unique = false, nullable = false, precision = 52, scale = 0)
    private double appliedSaleRate;

    @Column(name = "APPLIED_SALE_TAX", unique = false, nullable = false, precision = 52, scale = 0)
    private double appliedSaleTax;

    @Column(name = "APPLIED_MRP", unique = false, nullable = false, precision = 52, scale = 0)
    private double appliedSaleMRP;

    @Column(name = "APPLIED_VAT", unique = false, nullable = false, precision = 52, scale = 0)
    private double appliedSaleVAT;

    @Column(name = "OTHER_CHARGES", unique = false, nullable = false, precision = 52, scale = 0)
    private double otherCharges;

    @Column(name = "SALE_QTY", unique = false, nullable = false, precision = 52, scale = 0)
    private double saleQty;

    @Column(name = "SALE_AMOUNT", unique = false, nullable = false, precision = 52, scale = 0)
    private double saleItemAmount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "BILL_ID_FK")
    private BillSale billSaleFk;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID_FK")
    private ItemTable itemTableFk;

    @OneToMany(mappedBy = "saleItemBillFk", cascade = CascadeType.ALL/*, fetch = FetchType.LAZY/*, orphanRemoval = false,*/)
//    @JoinColumn(name = "RETURN_PUR_ID_FK")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<ReturnSaleBillItem> returnSaleBillList;

    public Long getSaleItemBillIdPk() {
        return saleItemBillIdPk;
    }

    public void setSaleItemBillIdPk(Long saleItemBillIdPk) {
        this.saleItemBillIdPk = saleItemBillIdPk;
    }

    public double getAppliedSaleRate() {
        return appliedSaleRate;
    }

    public void setAppliedSaleRate(double appliedSaleRate) {
        this.appliedSaleRate = appliedSaleRate;
    }

    public double getAppliedSaleTax() {
        return appliedSaleTax;
    }

    public void setAppliedSaleTax(double appliedSaleTax) {
        this.appliedSaleTax = appliedSaleTax;
    }

    public double getAppliedSaleMRP() {
        return appliedSaleMRP;
    }

    public void setAppliedSaleMRP(double appliedSaleMRP) {
        this.appliedSaleMRP = appliedSaleMRP;
    }

    public double getAppliedSaleVAT() {
        return appliedSaleVAT;
    }

    public void setAppliedSaleVAT(double appliedSaleVAT) {
        this.appliedSaleVAT = appliedSaleVAT;
    }

    public double getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(double otherCharges) {
        this.otherCharges = otherCharges;
    }

    public double getSaleQty() {
        return saleQty;
    }

    public void setSaleQty(double saleQty) {
        this.saleQty = saleQty;
    }

    public double getSaleItemAmount() {
        return saleItemAmount;
    }

    public void setSaleItemAmount(double saleItemAmount) {
        this.saleItemAmount = saleItemAmount;
    }

    public BillSale getBillSaleFk() {
        return billSaleFk;
    }

    public void setBillSaleFk(BillSale billSaleFk) {
        this.billSaleFk = billSaleFk;
    }

    public ItemTable getItemTableFk() {
        return itemTableFk;
    }

    public void setItemTableFk(ItemTable itemTableFk) {
        this.itemTableFk = itemTableFk;
    }

    public Set<ReturnSaleBillItem> getReturnSaleBillList() {
        return returnSaleBillList;
    }

    public void setReturnSaleBillList(Set<ReturnSaleBillItem> returnSaleBillList) {
        this.returnSaleBillList = returnSaleBillList;
    }

    
}
