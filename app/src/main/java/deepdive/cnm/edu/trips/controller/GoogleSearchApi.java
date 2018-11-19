package deepdive.cnm.edu.trips.controller;

import com.facebook.stetho.inspector.protocol.module.Network;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleSearchApi {


  @GET("/customsearch/v1")
  Response customSearch(@Query("key") String key, @Query("cx") String cx,
      @Query("q") String query);
}


