package deepdive.cnm.edu.trips.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import android.arch.persistence.room.Update;
import java.util.List;

import deepdive.cnm.edu.trips.model.entity.Flight;

@Dao
public interface  FlightDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insert(Flight flight);

    @Query("SELECT * FROM Flight ORDER BY flight_id")
    List<Flight> select();

    @Query("SELECT * FROM Flight WHERE person_id = :personId ORDER BY departure")
    List<Flight> select(long personId);

    @Query("SELECT * FROM Flight WHERE flight_id = :flightId LIMIT 1")
    Flight selectOne(long flightId);

    @Update
    int update(Flight flight);

    @Delete
    int delete(Flight flight);
}
