package trihk.hotelbooking.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import trihk.hotelbooking.entity.Account;
import trihk.hotelbooking.entity.BookingDetails;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-28T00:11:54")
@StaticMetamodel(Booking.class)
public class Booking_ { 

    public static volatile SingularAttribute<Booking, Integer> amount;
    public static volatile SingularAttribute<Booking, Date> updateDate;
    public static volatile SingularAttribute<Booking, Integer> discountPercent;
    public static volatile SingularAttribute<Booking, Integer> statusId;
    public static volatile CollectionAttribute<Booking, BookingDetails> bookingDetailsCollection;
    public static volatile SingularAttribute<Booking, Account> userEmail;
    public static volatile SingularAttribute<Booking, Integer> id;
    public static volatile SingularAttribute<Booking, Date> createDate;

}