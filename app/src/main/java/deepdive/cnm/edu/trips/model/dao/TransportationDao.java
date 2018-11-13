package deepdive.cnm.edu.trips.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import android.arch.persistence.room.Update;
import deepdive.cnm.edu.trips.model.entity.Flight;
import java.util.List;

import deepdive.cnm.edu.trips.model.entity.Transportation;

@Dao
public interface TransportationDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insert(Transportation transportation);

    @Query("SELECT * FROM Transportation ORDER BY pick_up")
    List<Transportation> select();

    @Query("SELECT * FROM Transportation WHERE person_id = :personId ORDER BY pick_up")
    List<Transportation> select(long personId);

    @Query("SELECT * FROM Transportation WHERE transportation_id = :transportationId LIMIT 1")
    Transportation selectOne(long transportationId);

    @Update
    int update(Transportation transportation);

    @Delete
    int delete(Transportation transportation);
}
