package trihk.hotelbooking.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import trihk.hotelbooking.entity.BookingDetails;
import trihk.hotelbooking.entity.Hotel;
import trihk.hotelbooking.entity.RoomType;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-28T00:11:54")
@StaticMetamodel(HotelRoom.class)
public class HotelRoom_ { 

    public static volatile SingularAttribute<HotelRoom, Boolean> isAvailable;
    public static volatile SingularAttribute<HotelRoom, Integer> price;
    public static volatile CollectionAttribute<HotelRoom, BookingDetails> bookingDetailsCollection;
    public static volatile SingularAttribute<HotelRoom, Integer> id;
    public static volatile SingularAttribute<HotelRoom, Hotel> hotelId;
    public static volatile SingularAttribute<HotelRoom, RoomType> roomType;

}