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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Eddy
 */
@Entity
public class ItemTable implements Serializable {

//    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ITEM_ID_PK", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemIdPk;

    @Column(name = "ITEM_NAME", unique = true, nullable = false, length = 30)
    private String itemName;

    @Column(name = "AVAILABLE_ITEMS", unique = false, nullable = false, precision = 52, scale = 0)
    private double availableItems;

    @Column(name = "ITEM_UNIT", unique = false, nullable = false, length = 20)
    private String itemUnit;

    @Column(name = "ITEM_LOW_ALERT_QTY", unique = false, nullable = false, precision = 52, scale = 0)
    private double itemLowAlertQty;

    @Column(name = "CATEGORY_NAME", unique = false, nullable = true, length = 30)
    private String categoryName;

    @Column(name = "ITEM_MRP", unique = false, nullable = false, precision = 52, scale = 0)
    private double itemMRP;

    @Column(name = "ITEM_VAT", unique = false, nullable = false, precision = 52, scale = 0)
    private double itemVAT;

    @Column(name = "ITEM_PUR_RATE", unique = false, nullable = false, precision = 52, scale = 0)
    private double itemPurRate;

    @Column(name = "ITEM_PUR_TAX", unique = false, nullable = false, precision = 52, scale = 0)
    private double itemPurchaseTax;

    @Column(name = "ITEM_SALE_RATE", unique = false, nullable = false, precision = 52, scale = 0)
    private double itemSaleRate;

    @Column(name = "ITEM_SALE_TAX", unique = false, nullable = false, precision = 52, scale = 0)
    private double itemSaleTax;

    @OneToMany(mappedBy = "itemTableFk", cascade = CascadeType.ALL/*, fetch = FetchType.LAZY/*, orphanRemoval = false,*/)
//    @JoinColumn(name = "SALE_ID_FK")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<SaleItemBill> saleItemBillList;

    @OneToMany(mappedBy = "itemTableFk", cascade = CascadeType.ALL/*, fetch = FetchType.LAZY/*, orphanRemoval = false,*/)
//    @JoinColumn(name = "SALE_ID_FK")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<SaleItemBill> purchaseItemBillList;

    @OneToMany(mappedBy = "itemTableFk", cascade = CascadeType.ALL/*, fetch = FetchType.LAZY/*, orphanRemoval = false,*/)
//    @JoinColumn(name = "SALE_ID_FK")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<SaleItemBill> saleItemChallanList;

    @OneToMany(mappedBy = "itemTableFk", cascade = CascadeType.ALL/*, fetch = FetchType.LAZY/*, orphanRemoval = false,*/)
//    @JoinColumn(name = "SALE_ID_FK")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<SaleItemBill> purchaseItemOrderList;

    public long getItemIdPk() {
        return itemIdPk;
    }

    public void setItemIdPk(long itemIdPk) {
        this.itemIdPk = itemIdPk;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(double availableItems) {
        this.availableItems = availableItems;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public double getItemLowAlertQty() {
        return itemLowAlertQty;
    }

    public void setItemLowAlertQty(double itemLowAlertQty) {
        this.itemLowAlertQty = itemLowAlertQty;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getItemMRP() {
        return itemMRP;
    }

    public void setItemMRP(double itemMRP) {
        this.itemMRP = itemMRP;
    }

    public double getItemVAT() {
        return itemVAT;
    }

    public void setItemVAT(double itemVAT) {
        this.itemVAT = itemVAT;
    }

    public double getItemPurRate() {
        return itemPurRate;
    }

    public void setItemPurRate(double itemPurRate) {
        this.itemPurRate = itemPurRate;
    }

    public double getItemPurchaseTax() {
        return itemPurchaseTax;
    }

    public void setItemPurchaseTax(double itemPurchaseTax) {
        this.itemPurchaseTax = itemPurchaseTax;
    }

    public double getItemSaleRate() {
        return itemSaleRate;
    }

    public void setItemSaleRate(double itemSaleRate) {
        this.itemSaleRate = itemSaleRate;
    }

    public double getItemSaleTax() {
        return itemSaleTax;
    }

    public void setItemSaleTax(double itemSaleTax) {
        this.itemSaleTax = itemSaleTax;
    }

    public Set<SaleItemBill> getSaleItemBillList() {
        return saleItemBillList;
    }

    public void setSaleItemBillList(Set<SaleItemBill> saleItemBillList) {
        this.saleItemBillList = saleItemBillList;
    }

    public Set<SaleItemBill> getPurchaseItemBillList() {
        return purchaseItemBillList;
    }

    public void setPurchaseItemBillList(Set<SaleItemBill> purchaseItemBillList) {
        this.purchaseItemBillList = purchaseItemBillList;
    }

    public Set<SaleItemBill> getSaleItemChallanList() {
        return saleItemChallanList;
    }

    public void setSaleItemChallanList(Set<SaleItemBill> saleItemChallanList) {
        this.saleItemChallanList = saleItemChallanList;
    }

    public Set<SaleItemBill> getPurchaseItemOrderList() {
        return purchaseItemOrderList;
    }

    public void setPurchaseItemOrderList(Set<SaleItemBill> purchaseItemOrderList) {
        this.purchaseItemOrderList = purchaseItemOrderList;
    }
    
    
}
