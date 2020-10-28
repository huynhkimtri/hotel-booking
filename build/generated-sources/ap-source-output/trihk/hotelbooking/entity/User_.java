package trihk.hotelbooking.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import trihk.hotelbooking.entity.Booking;
import trihk.hotelbooking.entity.UserRole;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-28T23:27:28")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> address;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, UserRole> roleId;
    public static volatile SingularAttribute<User, String> fullName;
    public static volatile SingularAttribute<User, Boolean> isActive;
    public static volatile CollectionAttribute<User, Booking> bookingCollection;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, Date> createDate;

}