/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.hibernate.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Eddy
 */
@Entity
public class OrderPurchase implements Serializable {

//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PUR_ORDER_ID_PK", unique = true, nullable = false)
    private Long purchaseOrderIdPk;

    @Column(name = "ORDER_NO", unique = true, updatable = false, nullable = false, length = 60)
    private String orderNo;

    @Column(name = "CLIENTS_ORDER_REF", unique = false, updatable = false, nullable = true, length = 60)
    private String clientsOrderRef;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CHALLAN_GEN_DATE", nullable = true, length = 29)
    private Date challanGenerateDate;

    @Column(name = "GRAND_TOTAL", nullable = false, precision = 52, scale = 0)
    private double grandTotal;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID_FK")
    private ClientTable clientTableFk;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "BILL_PREFIX_ID_FK")
    private BillPrefixTable billPrefixTableFk;

    @OneToMany(mappedBy = "orderPurchaseFk", cascade = CascadeType.ALL/*, fetch = FetchType.LAZY/*, orphanRemoval = false,*/)
//    @JoinColumn(name = "PURCHASE_ID_FK")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<PurchaseItemChallan> orderItemPurchaseList;

    public Long getPurchaseOrderIdPk() {
        return purchaseOrderIdPk;
    }

    public void setPurchaseOrderIdPk(Long purchaseOrderIdPk) {
        this.purchaseOrderIdPk = purchaseOrderIdPk;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getClientsOrderRef() {
        return clientsOrderRef;
    }

    public void setClientsOrderRef(String clientsOrderRef) {
        this.clientsOrderRef = clientsOrderRef;
    }

    public Date getChallanGenerateDate() {
        return challanGenerateDate;
    }

    public void setChallanGenerateDate(Date challanGenerateDate) {
        this.challanGenerateDate = challanGenerateDate;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public ClientTable getClientTableFk() {
        return clientTableFk;
    }

    public void setClientTableFk(ClientTable clientTableFk) {
        this.clientTableFk = clientTableFk;
    }

    public BillPrefixTable getBillPrefixTableFk() {
        return billPrefixTableFk;
    }

    public void setBillPrefixTableFk(BillPrefixTable billPrefixTableFk) {
        this.billPrefixTableFk = billPrefixTableFk;
    }

    public Set<PurchaseItemChallan> getOrderItemPurchaseList() {
        return orderItemPurchaseList;
    }

    public void setOrderItemPurchaseList(Set<PurchaseItemChallan> orderItemPurchaseList) {
        this.orderItemPurchaseList = orderItemPurchaseList;
    }

    
}
