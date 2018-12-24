package coder.adnan.tourmate.tourmateapps1.nearby;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NearbyService {
    @GET
    Call<NearbyPlaceResponse> getNearbyPlaces(@Url String endUrl);
}
