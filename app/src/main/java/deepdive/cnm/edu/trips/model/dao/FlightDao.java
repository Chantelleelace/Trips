package deepdive.cnm.edu.trips.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import deepdive.cnm.edu.trips.model.entity.Flight;

@Dao
public interface FlightDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insert(Flight flight);

    @Query("SELECT * FROM Flight ORDER BY flight_id")
    List<Flight> select();

    @Query("SELECT * FROM Flight WHERE person_id = :personId ORDER BY departure_date")
    List<Flight> select(long personId);

    @Delete
    int delete(List<Flight> flights);
}