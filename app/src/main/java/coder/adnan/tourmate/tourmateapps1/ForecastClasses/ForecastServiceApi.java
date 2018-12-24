package coder.adnan.tourmate.tourmateapps1.ForecastClasses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ForecastServiceApi {

    @GET()
    Call<ForeCastWeather> getForecastWeather(@Url String urlString);
}
