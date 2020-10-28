package trihk.hotelbooking.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import trihk.hotelbooking.entity.HotelRoom;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-28T23:27:28")
@StaticMetamodel(RoomType.class)
public class RoomType_ { 

    public static volatile SingularAttribute<RoomType, String> description;
    public static volatile CollectionAttribute<RoomType, HotelRoom> hotelRoomCollection;
    public static volatile SingularAttribute<RoomType, Integer> id;

}