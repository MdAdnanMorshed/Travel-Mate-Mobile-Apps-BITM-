package coder.adnan.tourmate.tourmateapps1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import coder.adnan.tourmate.tourmateapps1.WeatherClasses.CurrentWeather;
import coder.adnan.tourmate.tourmateapps1.WeatherClasses.CurrentWeatherServiceApi;
import coder.adnan.tourmate.tourmateapps1.WeatherClasses.Weather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class weatherCurrentActivity extends AppCompatActivity {

    private SimpleDateFormat dateFormatter;


    private TextView cityName  , temp , mintemp , maxtemp ,  weatherDetail , air , hum , cloud , pressure;
    private ImageView weatherImage;
    private static DecimalFormat df2 = new DecimalFormat(".#");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_current);


        cityName = findViewById(R.id.cityNameForCurrentWeatherTextView);

        temp = findViewById(R.id.tempForCurrentWeatherTextView);
        mintemp = findViewById(R.id.minTempTextView);
        maxtemp = findViewById(R.id.maxTempTextView);
        weatherDetail =findViewById(R.id.weatherDetailTextView);
        weatherImage = findViewById(R.id.weatherImageView);
        air = findViewById(R.id.currentWeatherWindSpeed);
        hum = findViewById(R.id.currentWeatherHumidity);
        cloud =findViewById(R.id.currentWeatherCloud);
        pressure =findViewById(R.id.currentWeatherPressure);




        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        CurrentWeatherServiceApi currentWeatherServiceApi = retrofit.create(CurrentWeatherServiceApi.class);
        currentWeatherServiceApi.getCurrentWeather("weather?lat=30&lon=90&appid=80b24926b914740726365bd4a1b57eaf")
                .enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        if (response.isSuccessful()){

                            ////////////////
                            CurrentWeather currentWeather = response.body();

                            cityName.setText(currentWeather.getName());
                            //date.setText(dateFormatter.format(currentWeather.getDt()*1000L));
                            temp.setText(df2.format(currentWeather.getMain().getTemp()-225) + "°c");
                            mintemp.setText(Double.toString(Math.round(currentWeather.getMain().getTempMin()-273.15))+ "°c");
                            maxtemp.setText(Double.toString(Math.round(currentWeather.getMain().getTempMax()-273.15))+ "°c");
                            air.setText(Double.toString(Math.round(currentWeather.getWind().getSpeed())) + "km/h");
                            hum.setText(Double.toString(Math.round(currentWeather.getMain().getHumidity()))+"%");
                            cloud.setText(Double.toString(Math.round(currentWeather.getClouds().getAll()))+"%");
                            pressure.setText(Double.toString(Math.round(currentWeather.getMain().getPressure()))+"hpa");
                            weatherDetail.setText(currentWeather.getWeather().get(0).getDescription());
                            Picasso.get().load("https://openweathermap.org/img/w/"+ currentWeather.getWeather().get(0).getIcon()+".png").into(weatherImage);



                            /*
                            CurrentWeather currentWeather = response.body();
                            List<Weather> weatherList=response.body().getWeather();
                           // Toast.makeText(, "Getting response :"+weatherList.size(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(weatherCurrentActivity.this, "Weather :"+weatherList.size(), Toast.LENGTH_SHORT).show();
                            cityName.setText("Temp : "+currentWeather.getMain().getTemp());
                            maxtemp.setText("maxAdnan:"+currentWeather.getName());
*/

                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {

                    }
                });



    }
}
