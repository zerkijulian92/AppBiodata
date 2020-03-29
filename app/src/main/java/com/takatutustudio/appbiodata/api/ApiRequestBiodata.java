package com.takatutustudio.appbiodata.api;

import com.takatutustudio.appbiodata.model.ResponsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRequestBiodata {
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponsModel> sendBiodata(@Field("nama") String nama,
                                   @Field("usia") String usia,
                                   @Field("domisili") String domisili);
}
