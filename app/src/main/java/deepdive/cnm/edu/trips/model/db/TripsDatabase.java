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

/**
 * The type Trips database.
 */
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

    /**
     * Gets flight dao.
     *
     * @return the flight dao
     */
    public abstract FlightDao getFlightDao();

    /**
     * Gets hotel dao.
     *
     * @return the hotel dao
     */
    public abstract HotelDao getHotelDao();

    /**
     * Gets person dao.
     *
     * @return the person dao
     */
    public abstract PersonDao getPersonDao();

    /**
     * Gets transportation dao.
     *
     * @return the transportation dao
     */
    public abstract TransportationDao getTransportationDao();

    /**
     * Gets instance.
     *
     * @param context the context
     * @return the instance
     */
    public synchronized static TripsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), TripsDatabase.class, DB_NAME)
                    .build();
        }
        return instance;
    }

    /**
     * Forget instance.
     */
    public synchronized static void forgetInstance() { instance = null; }

    /**
     * The type Converters.
     */
    public static class Converters {

        /**
         * Date from long date.
         *
         * @param time the time
         * @return the date
         */
        @TypeConverter
        public static Date dateFromLong(Long time) { return (time != null) ? new Date(time) : null; }

        /**
         * Long from date long.
         *
         * @param date the date
         * @return the long
         */
        @TypeConverter
        public static Long longFromDate(Date date) { return (date != null) ? date.getTime() : null; }
    }


}
