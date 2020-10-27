/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.hotelbooking.entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TriHuynh
 */
@Entity
@Table(name = "hotel_room", catalog = "HotelBooking", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HotelRoom.findAll", query = "SELECT h FROM HotelRoom h"),
    @NamedQuery(name = "HotelRoom.findById", query = "SELECT h FROM HotelRoom h WHERE h.id = :id"),
    @NamedQuery(name = "HotelRoom.findByPrice", query = "SELECT h FROM HotelRoom h WHERE h.price = :price"),
    @NamedQuery(name = "HotelRoom.findByIsAvailable", query = "SELECT h FROM HotelRoom h WHERE h.isAvailable = :isAvailable")})
public class HotelRoom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "price")
    private Integer price;
    @Column(name = "is_available")
    private Boolean isAvailable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomId")
    private Collection<BookingDetails> bookingDetailsCollection;
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    @ManyToOne
    private Hotel hotelId;
    @JoinColumn(name = "room_type", referencedColumnName = "id")
    @ManyToOne
    private RoomType roomType;

    public HotelRoom() {
    }

    public HotelRoom(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @XmlTransient
    public Collection<BookingDetails> getBookingDetailsCollection() {
        return bookingDetailsCollection;
    }

    public void setBookingDetailsCollection(Collection<BookingDetails> bookingDetailsCollection) {
        this.bookingDetailsCollection = bookingDetailsCollection;
    }

    public Hotel getHotelId() {
        return hotelId;
    }

    public void setHotelId(Hotel hotelId) {
        this.hotelId = hotelId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
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
        if (!(object instanceof HotelRoom)) {
            return false;
        }
        HotelRoom other = (HotelRoom) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trihk.hotelbooking.entity.HotelRoom[ id=" + id + " ]";
    }
    
}
