/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TriHuynh
 */
@Entity
@Table(name = "booking_details", catalog = "HotelBooking", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BookingDetails.findAll", query = "SELECT b FROM BookingDetails b"),
    @NamedQuery(name = "BookingDetails.findById", query = "SELECT b FROM BookingDetails b WHERE b.id = :id"),
    @NamedQuery(name = "BookingDetails.findByAmount", query = "SELECT b FROM BookingDetails b WHERE b.amount = :amount"),
    @NamedQuery(name = "BookingDetails.findByUnitPrice", query = "SELECT b FROM BookingDetails b WHERE b.unitPrice = :unitPrice"),
    @NamedQuery(name = "BookingDetails.findByPeriod", query = "SELECT t FROM BookingDetails t WHERE t.checkinDate <= :endDate AND t.checkoutDate >= :startDate"),
    @NamedQuery(name = "BookingDetails.findRoomInPeriod", query = "SELECT t FROM BookingDetails t WHERE t.checkinDate <= :endDate AND t.checkoutDate >= :startDate AND t.roomId = :roomId"),
    @NamedQuery(name = "BookingDetails.findByCheckinDate", query = "SELECT b FROM BookingDetails b WHERE b.checkinDate = :checkinDate"),
    @NamedQuery(name = "BookingDetails.findByCheckoutDate", query = "SELECT b FROM BookingDetails b WHERE b.checkoutDate = :checkoutDate"),
    @NamedQuery(name = "BookingDetails.findByCreateDate", query = "SELECT b FROM BookingDetails b WHERE b.createDate = :createDate")})
public class BookingDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private int amount;
    @Basic(optional = false)
    @Column(name = "unit_price", nullable = false)
    private int unitPrice;
    @Column(name = "checkin_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkinDate;
    @Column(name = "checkout_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkoutDate;
    @Basic(optional = false)
    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @JoinColumn(name = "booking_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Booking bookingId;
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private HotelRoom roomId;

    public BookingDetails() {
    }

    public BookingDetails(Integer id) {
        this.id = id;
    }

    public BookingDetails(Integer id, int amount, int unitPrice, Date createDate) {
        this.id = id;
        this.amount = amount;
        this.unitPrice = unitPrice;
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

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Booking getBookingId() {
        return bookingId;
    }

    public void setBookingId(Booking bookingId) {
        this.bookingId = bookingId;
    }

    public HotelRoom getRoomId() {
        return roomId;
    }

    public void setRoomId(HotelRoom roomId) {
        this.roomId = roomId;
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
        if (!(object instanceof BookingDetails)) {
            return false;
        }
        BookingDetails other = (BookingDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trihk.hotelbooking.entity.BookingDetails[ id=" + id + " ]";
    }

}
