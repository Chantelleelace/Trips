package deepdive.cnm.edu.trips.model.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import java.util.Date;

import deepdive.cnm.edu.trips.model.dao.FlightDao;
import deepdive.cnm.edu.trips.model.dao.HotelDao;
import deepdive.cnm.edu.trips.model.dao.PersonDao;
import deepdive.cnm.edu.trips.model.dao.TransportationDao;
import deepdive.cnm.edu.trips.model.entity.Flight;
import deepdive.cnm.edu.trips.model.entity.Hotel;
import deepdive.cnm.edu.trips.model.entity.Person;
import deepdive.cnm.edu.trips.model.entity.Transportation;
import deepdive.cnm.edu.trips.model.db.TripsDatabase.Converters;

@Database(
        entities = {
            Flight.class,
            Hotel.class,
            Person.class,
            Transportation.class},
        version = 1,
        exportSchema = true
)
@TypeConverters(Converters.class)
public abstract class TripsDatabase extends RoomDatabase {

    private static final String DB_NAME = "trips_db";

    private static TripsDatabase instance = null;

    public abstract FlightDao getFlightDao();

    public abstract HotelDao getHotelDao();

    public abstract PersonDao getPersonDao();

    public abstract TransportationDao getTransportationDao();

    public synchronized static TripsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), TripsDatabase.class, DB_NAME)
                    .build();
        }
        return instance;
    }

    public synchronized static void forgetInstance() { instance = null; }

    public static class Converters {

        @TypeConverter
        public static Date dateFromLong(Long time) { return (time != null) ? new Date(time) : null; }

        @TypeConverter
        public static Long longFromDate(Date date) { return (date != null) ? date.getTime() : null; }
    }


}
