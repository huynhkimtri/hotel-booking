package trihk.hotelbooking.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import trihk.hotelbooking.entity.HotelArea;
import trihk.hotelbooking.entity.HotelRoom;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-28T00:11:54")
@StaticMetamodel(Hotel.class)
public class Hotel_ { 

    public static volatile SingularAttribute<Hotel, HotelArea> areaId;
    public static volatile SingularAttribute<Hotel, String> imageUrl;
    public static volatile SingularAttribute<Hotel, String> name;
    public static volatile SingularAttribute<Hotel, String> description;
    public static volatile CollectionAttribute<Hotel, HotelRoom> hotelRoomCollection;
    public static volatile SingularAttribute<Hotel, Integer> id;

}