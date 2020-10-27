/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TriHuynh
 */
@Entity
@Table(name = "booking", catalog = "HotelBooking", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b"),
    @NamedQuery(name = "Booking.findById", query = "SELECT b FROM Booking b WHERE b.id = :id"),
    @NamedQuery(name = "Booking.findByUserEmail", query = "SELECT b FROM Booking b WHERE b.userEmail.email = :email"),
    @NamedQuery(name = "Booking.findByAmount", query = "SELECT b FROM Booking b WHERE b.amount = :amount"),
    @NamedQuery(name = "Booking.findByStatusId", query = "SELECT b FROM Booking b WHERE b.statusId = :statusId"),
    @NamedQuery(name = "Booking.findByCreateDate", query = "SELECT b FROM Booking b WHERE b.createDate = :createDate"),
    @NamedQuery(name = "Booking.findByUpdateDate", query = "SELECT b FROM Booking b WHERE b.updateDate = :updateDate"),
    @NamedQuery(name = "Booking.findByDiscountPercent", query = "SELECT b FROM Booking b WHERE b.discountPercent = :discountPercent")})
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private int amount;
    @Column(name = "status_id")
    private Integer statusId;
    @Basic(optional = false)
    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "discount_percent")
    private Integer discountPercent;
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    @ManyToOne
    private Account userEmail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<BookingDetails> bookingDetailsCollection;

    public Booking() {
    }

    public Booking(Integer id) {
        this.id = id;
    }

    public Booking(Integer id, int amount, Date createDate) {
        this.id = id;
        this.amount = amount;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Account getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(Account userEmail) {
        this.userEmail = userEmail;
    }

    @XmlTransient
    public Collection<BookingDetails> getBookingDetailsCollection() {
        return bookingDetailsCollection;
    }

    public void setBookingDetailsCollection(Collection<BookingDetails> bookingDetailsCollection) {
        this.bookingDetailsCollection = bookingDetailsCollection;
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
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trihk.hotelbooking.entity.Booking[ id=" + id + " ]";
    }

}
