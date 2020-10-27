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
    @NamedQuery(name = "BookingDetails.findByQuantity", query = "SELECT b FROM BookingDetails b WHERE b.quantity = :quantity"),
    @NamedQuery(name = "BookingDetails.findBySinglePrice", query = "SELECT b FROM BookingDetails b WHERE b.singlePrice = :singlePrice"),
    @NamedQuery(name = "BookingDetails.findByCreateDate", query = "SELECT b FROM BookingDetails b WHERE b.createDate = :createDate"),
    @NamedQuery(name = "BookingDetails.findByUpdateDate", query = "SELECT b FROM BookingDetails b WHERE b.updateDate = :updateDate")})
public class BookingDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic(optional = false)
    @Column(name = "single_price", nullable = false)
    private int singlePrice;
    @Basic(optional = false)
    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Booking orderId;
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private HotelRoom roomId;

    public BookingDetails() {
    }

    public BookingDetails(Integer id) {
        this.id = id;
    }

    public BookingDetails(Integer id, int quantity, int singlePrice, Date createDate) {
        this.id = id;
        this.quantity = quantity;
        this.singlePrice = singlePrice;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(int singlePrice) {
        this.singlePrice = singlePrice;
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

    public Booking getOrderId() {
        return orderId;
    }

    public void setOrderId(Booking orderId) {
        this.orderId = orderId;
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
