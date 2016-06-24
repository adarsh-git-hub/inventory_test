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
public class ChallanSale implements Serializable {

//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SALE_CHALLAN_ID_PK", unique = true, nullable = false)
    private long saleChallanIdPk;

    @Column(name = "CHALLAN_NO", unique = true, updatable = false, nullable = false, length = 60)
    private String challanNo;

//    @Column(name = "CLIENTS_BILL_NO", unique = false, updatable = false, nullable = true, length = 60)
//    private String billNoByClient;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CHALLAN_GEN_DATE", nullable = true, length = 29)
    private Date challanGenerateDate;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "CHALLAN_RECEIVED_DATE", nullable = true, length = 29)
//    private Date challanReceivedDate;
//    @Column(name = "SALE_OR_PURCHASE", nullable = false)
//    private boolean saleOrPurchase;
    @Column(name = "GRAND_TOTAL", nullable = false, precision = 52, scale = 0)
    private double grandTotal;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID_FK")
    private ClientTable clientTableFk;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "BILL_PREFIX_ID_FK")
    private BillPrefixTable billPrefixTableFk;

    @OneToMany(mappedBy = "challanSaleFk", cascade = CascadeType.ALL/*, fetch = FetchType.LAZY/*, orphanRemoval = false,*/)
//    @JoinColumn(name = "PURCHASE_ID_FK")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Set<SaleItemChallan> challanItemSaleList;

    public long getSaleChallanIdPk() {
        return saleChallanIdPk;
    }

    public void setSaleChallanIdPk(long saleChallanIdPk) {
        this.saleChallanIdPk = saleChallanIdPk;
    }

    public String getChallanNo() {
        return challanNo;
    }

    public void setChallanNo(String challanNo) {
        this.challanNo = challanNo;
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

    public Set<SaleItemChallan> getChallanItemSaleList() {
        return challanItemSaleList;
    }

    public void setChallanItemSaleList(Set<SaleItemChallan> challanItemSaleList) {
        this.challanItemSaleList = challanItemSaleList;
    }

    
}
