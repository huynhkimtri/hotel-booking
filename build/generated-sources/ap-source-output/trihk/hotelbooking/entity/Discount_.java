package trihk.hotelbooking.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-28T00:11:54")
@StaticMetamodel(Discount.class)
public class Discount_ { 

    public static volatile SingularAttribute<Discount, String> code;
    public static volatile SingularAttribute<Discount, Integer> discountPercent;
    public static volatile SingularAttribute<Discount, Integer> id;
    public static volatile SingularAttribute<Discount, Date> expDate;

}