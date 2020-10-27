package trihk.hotelbooking.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import trihk.hotelbooking.entity.Booking;
import trihk.hotelbooking.entity.HotelRoom;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-28T00:11:54")
@StaticMetamodel(BookingDetails.class)
public class BookingDetails_ { 

    public static volatile SingularAttribute<BookingDetails, Integer> singlePrice;
    public static volatile SingularAttribute<BookingDetails, Date> updateDate;
    public static volatile SingularAttribute<BookingDetails, Integer> quantity;
    public static volatile SingularAttribute<BookingDetails, Booking> orderId;
    public static volatile SingularAttribute<BookingDetails, Integer> id;
    public static volatile SingularAttribute<BookingDetails, HotelRoom> roomId;
    public static volatile SingularAttribute<BookingDetails, Date> createDate;

}