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
import javax.persistence.OneToMany;

/**
 *
 * @author Eddy
 */
@Entity
public class ClientTable implements Serializable {

//    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "CLIENT_ID_PK", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long clientIdPk;

    @Column(name = "CLIENT_NAME", unique = true, nullable = false, length = 30)
    private String clientName;

    @Column(name = "CLIENT_TYPE", unique = false, nullable = false, length = 20)
    private String clientType;

    @Column(name = "CLIENT_PHONE1", unique = false, nullable = true)
    private long clientPhone1;

    @Column(name = "CLIENT_PHONE2", unique = false, nullable = true)
    private long clientPhone2;

    @Column(name = "CLIENT_EMAIL", length = 50, unique = false, nullable = true)
    private String clientEmail;

    @Column(name = "CLIENT_ADDRESS", length = 50, unique = false, nullable = true)
    private String clientAddress;

    @Column(name = "CLIENT_TIN", length = 20, unique = false, nullable = true)
    private String clientTin;

    @Column(name = "CLIENT_SERVICE_NUMBER", length = 20, unique = false, nullable = true)
    private String clientServiceNumber;

//    @Column(name = "PREV_SALE_ADJUST", nullable = false, precision = 52, scale = 0)
//    private double previousSaleAdjustment;
//
//    @Column(name = "PREV_PUR_ADJUST", nullable = false, precision = 52, scale = 0)
//    private double previousPurchaseAdjustment;
    @Column(name = "DUE_SALE_AMOUNT", nullable = false, precision = 52, scale = 0)
    private double dueSaleAmount;

    @Column(name = "DUE_PUR_AMOUNT", nullable = false, precision = 52, scale = 0)
    private double duePurchaseAmount;

    @OneToMany(mappedBy = "clientTableFk", cascade = CascadeType.ALL, fetch = FetchType.LAZY/*, orphanRemoval = true*/)
//    @JoinColumn(name = "PRODUCT_ID_FK")
    private Set<BillSale> billListSale;

    @OneToMany(mappedBy = "clientTableFk", cascade = CascadeType.ALL, fetch = FetchType.LAZY/*, orphanRemoval = true*/)
//    @JoinColumn(name = "PRODUCT_ID_FK")
    private Set<BillPurchase> billListPurchase;

    @OneToMany(mappedBy = "clientTableFk", cascade = CascadeType.ALL, fetch = FetchType.LAZY/*, orphanRemoval = true*/)
//    @JoinColumn(name = "PRODUCT_ID_FK")
    private Set<ChallanSale> challanListSale;

    @OneToMany(mappedBy = "clientTableFk", cascade = CascadeType.ALL, fetch = FetchType.LAZY/*, orphanRemoval = true*/)
//    @JoinColumn(name = "PRODUCT_ID_FK")
    private Set<OrderPurchase> challanListPurchase;

    @OneToMany(mappedBy = "clientTableFk", cascade = CascadeType.ALL, fetch = FetchType.LAZY/*, orphanRemoval = true*/)
//    @JoinColumn(name = "PRODUCT_ID_FK")
    private Set<PaymentByClients> paymentByClientList;

    @OneToMany(mappedBy = "clientTableFk", cascade = CascadeType.ALL, fetch = FetchType.LAZY/*, orphanRemoval = true*/)
//    @JoinColumn(name = "PRODUCT_ID_FK")
    private Set<PaymentByUs> paymentByUsList;

    public long getClientIdPk() {
        return clientIdPk;
    }

    public void setClientIdPk(long clientIdPk) {
        this.clientIdPk = clientIdPk;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public long getClientPhone1() {
        return clientPhone1;
    }

    public void setClientPhone1(long clientPhone1) {
        this.clientPhone1 = clientPhone1;
    }

    public long getClientPhone2() {
        return clientPhone2;
    }

    public void setClientPhone2(long clientPhone2) {
        this.clientPhone2 = clientPhone2;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientTin() {
        return clientTin;
    }

    public void setClientTin(String clientTin) {
        this.clientTin = clientTin;
    }

    public String getClientServiceNumber() {
        return clientServiceNumber;
    }

    public void setClientServiceNumber(String clientServiceNumber) {
        this.clientServiceNumber = clientServiceNumber;
    }

    public double getDueSaleAmount() {
        return dueSaleAmount;
    }

    public void setDueSaleAmount(double dueSaleAmount) {
        this.dueSaleAmount = dueSaleAmount;
    }

    public double getDuePurchaseAmount() {
        return duePurchaseAmount;
    }

    public void setDuePurchaseAmount(double duePurchaseAmount) {
        this.duePurchaseAmount = duePurchaseAmount;
    }

    public Set<BillSale> getBillListSale() {
        return billListSale;
    }

    public void setBillListSale(Set<BillSale> billListSale) {
        this.billListSale = billListSale;
    }

    public Set<BillPurchase> getBillListPurchase() {
        return billListPurchase;
    }

    public void setBillListPurchase(Set<BillPurchase> billListPurchase) {
        this.billListPurchase = billListPurchase;
    }

    public Set<ChallanSale> getChallanListSale() {
        return challanListSale;
    }

    public void setChallanListSale(Set<ChallanSale> challanListSale) {
        this.challanListSale = challanListSale;
    }

    public Set<OrderPurchase> getChallanListPurchase() {
        return challanListPurchase;
    }

    public void setChallanListPurchase(Set<OrderPurchase> challanListPurchase) {
        this.challanListPurchase = challanListPurchase;
    }

    public Set<PaymentByClients> getPaymentByClientList() {
        return paymentByClientList;
    }

    public void setPaymentByClientList(Set<PaymentByClients> paymentByClientList) {
        this.paymentByClientList = paymentByClientList;
    }

    public Set<PaymentByUs> getPaymentByUsList() {
        return paymentByUsList;
    }

    public void setPaymentByUsList(Set<PaymentByUs> paymentByUsList) {
        this.paymentByUsList = paymentByUsList;
    }

}
