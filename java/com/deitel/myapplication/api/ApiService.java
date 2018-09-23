package com.deitel.myapplication.api;

import com.deitel.myapplication.model.UsersList;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
 
    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of ContactList
    */

    @GET("/api/users?page=1")
    Call<UsersList> getMyJSON();
}