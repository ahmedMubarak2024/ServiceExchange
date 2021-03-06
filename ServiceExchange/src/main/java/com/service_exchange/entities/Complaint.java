/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service_exchange.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Nouran
 */
@Entity
@Table(name = "complaint")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Complaint.findAll", query = "SELECT c FROM Complaint c")
        , @NamedQuery(name = "Complaint.findById", query = "SELECT c FROM Complaint c WHERE c.id = :id")
        , @NamedQuery(name = "Complaint.findByState", query = "SELECT c FROM Complaint c WHERE c.state = :state")
        , @NamedQuery(name = "Complaint.findByDescription", query = "SELECT c FROM Complaint c WHERE c.description = :description")
        , @NamedQuery(name = "Complaint.findByDate", query = "SELECT c FROM Complaint c WHERE c.date = :date")})
public class Complaint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "state")
    private String state;
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @OneToMany(mappedBy = "complaintId")
    @JsonIgnore
    private Collection<Message> messageCollection;
    @JoinColumn(name = "reviewed_by", referencedColumnName = "email")
    @ManyToOne
    private AdminTable reviewedBy;
    @JoinColumn(name = "transaction_id", referencedColumnName = "id")
    @ManyToOne
    private TransactionInfo transactionId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private UserTable userId;

    @Transient
    public final static String NOT_REVIEWED_STATE="Not_Reviewed";
    @Transient
    public final static String ON_REVIEW_STATE="On_Review";
    @Transient
    public final static String ACCEPTED_STATE="Accepted";
    @Transient
    public final static String REJECTED_STATE="Rejected";
    @Transient
    public final static String COMPLETED = "Completed";
    public Complaint() {
    }

    public Complaint(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    public AdminTable getReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(AdminTable reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public TransactionInfo getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(TransactionInfo transactionId) {
        this.transactionId = transactionId;
    }

    public UserTable getUserId() {
        return userId;
    }

    public void setUserId(UserTable userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Complaint)) {
            return false;
        }
        Complaint other = (Complaint) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.service_exchange.Complaint[ id=" + id + " ]";
    }

}
