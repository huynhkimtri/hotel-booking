package trihk.hotelbooking.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import trihk.hotelbooking.entity.AccountRole;
import trihk.hotelbooking.entity.Booking;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-28T00:11:54")
@StaticMetamodel(Account.class)
public class Account_ { 

    public static volatile SingularAttribute<Account, String> password;
    public static volatile SingularAttribute<Account, Date> updateDate;
    public static volatile SingularAttribute<Account, String> address;
    public static volatile SingularAttribute<Account, String> phone;
    public static volatile SingularAttribute<Account, AccountRole> roleId;
    public static volatile SingularAttribute<Account, String> fullName;
    public static volatile SingularAttribute<Account, Boolean> isActive;
    public static volatile CollectionAttribute<Account, Booking> bookingCollection;
    public static volatile SingularAttribute<Account, String> email;
    public static volatile SingularAttribute<Account, Date> createDate;

}