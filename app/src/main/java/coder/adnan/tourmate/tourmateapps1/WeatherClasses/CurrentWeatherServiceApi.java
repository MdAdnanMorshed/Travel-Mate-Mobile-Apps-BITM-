package coder.adnan.tourmate.tourmateapps1.WeatherClasses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CurrentWeatherServiceApi {
    @GET()
    Call<CurrentWeather> getCurrentWeather(@Url String urlString);
}
