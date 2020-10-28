package trihk.hotelbooking.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import trihk.hotelbooking.entity.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-28T23:27:28")
@StaticMetamodel(UserRole.class)
public class UserRole_ { 

    public static volatile CollectionAttribute<UserRole, User> userCollection;
    public static volatile SingularAttribute<UserRole, String> name;
    public static volatile SingularAttribute<UserRole, Integer> id;

}