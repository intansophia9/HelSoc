package com.example.james.applogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HelpOut{
    @POST("1FAIpQLSfthCt0Hpi0q31bKlWFXGeAbwI-Sf2L9yKgnhYRHpUZHTRAUQ/formResponse")
    @FormUrlEncoded
    Call<Void> feedbackSend(
            @Field("entry.1852156891") String feedback,
            @Field("entry.2054077185") String name,
            @Field("entry.244779672") String email
    );
}
