package deepdive.cnm.edu.trips.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import android.arch.persistence.room.Update;
import deepdive.cnm.edu.trips.model.entity.Flight;
import java.util.List;

import deepdive.cnm.edu.trips.model.entity.Hotel;

@Dao
public interface HotelDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insert(Hotel hotel);

    @Query("SELECT * FROM Hotel ORDER BY check_in")
    List<Hotel> select();

    @Query("SELECT * FROM Hotel WHERE person_id = :personId ORDER BY check_in")
    List<Hotel> select(long personId);

    @Query("SELECT * FROM Hotel WHERE hotel_id = :hotelId LIMIT 1")
    Hotel selectOne(long hotelId);

    @Update
    int update(Hotel hotel);

    @Delete
    int delete(Hotel hotel);

}
