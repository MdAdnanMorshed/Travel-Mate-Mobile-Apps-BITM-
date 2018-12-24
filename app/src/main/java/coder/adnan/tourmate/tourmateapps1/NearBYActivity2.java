package coder.adnan.tourmate.tourmateapps1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;



import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import java.util.List;

import coder.adnan.tourmate.tourmateapps1.nearby.NearbyPlaceResponse;
import coder.adnan.tourmate.tourmateapps1.nearby.NearbyService;
import coder.adnan.tourmate.tourmateapps1.nearby.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NearBYActivity2 extends AppCompatActivity implements OnMapReadyCallback {

    private Spinner maptype;
    private GoogleMap mMap;
    private ClusterManager<MyItems> clusterManager;
    int PLACE_PICKER_REQUEST = 1;
    private final String BASE_URL = "https://maps.googleapis.com/maps/api/";
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    String googleMapType[]={"MAP_TYPE_NONE","MAP_TYPE_NORMAL","MAP_TYPE_SATELLITE","MAP_TYPE_TERRAIN"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
         maptype=findViewById(R.id.mapTypeSP);
        ArrayAdapter<String> mapty=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,googleMapType);
        maptype.setAdapter(mapty);

        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        clusterManager = new ClusterManager<>(this, mMap);
        mMap.setOnCameraIdleListener(clusterManager);
        mMap.setOnMarkerClickListener(clusterManager);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);







        // Add a marker in Sydney and move the camera
        LatLng CurrentLocation = new LatLng(23.741483, 90.4245368);
        mMap.addMarker(new MarkerOptions().position(CurrentLocation).title("My House").snippet("North Shajanpur Dhaka-1217"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CurrentLocation,15f));
        UiSettings ut=mMap.getUiSettings();
        ut.setZoomControlsEnabled(true);

        getNearbyPlaces();
    }

    private void getNearbyPlaces() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NearbyService nearbyService = retrofit.create(NearbyService.class);
        String apikey ="AIzaSyBz0QzCaukHIGO765nkfp7BqEQsPo39Ufo";
        String endUrl = String.format("place/nearbysearch/json?location=23.741483,90.4245368&radius=500&type=restaurant&key=%s",apikey);
        nearbyService.getNearbyPlaces(endUrl)
                .enqueue(new Callback<NearbyPlaceResponse>() {
                    @Override
                    public void onResponse(Call<NearbyPlaceResponse> call, Response<NearbyPlaceResponse> response) {
                        if(response.isSuccessful()){
                            NearbyPlaceResponse placeResponse = response.body();
                            List<Result>results = placeResponse.getResults();
                            Toast.makeText(NearBYActivity2.this, "size: "+results.size(), Toast.LENGTH_SHORT).show();
                           // Toast.makeText(NearBYActivity2.this, "", Toast.LENGTH_SHORT).show();
                            for(Result result : results){
                                double lat = result.getGeometry().getLocation().getLat();
                                double lng = result.getGeometry().getLocation().getLng();
                                LatLng latLng = new LatLng(lat, lng);
                                mMap.addMarker(new MarkerOptions().position(latLng)
                                .title(result.getName()).snippet(result.getVicinity()));
                                MyItems item = new MyItems(lat, lng, result.getName(), result.getVicinity());
                                clusterManager.addItem(item);
                                clusterManager.cluster();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NearbyPlaceResponse> call, Throwable t) {

                    }
                });
    }

    public void showPlacePickerDialog(View view) {



    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PLACE_PICKER_REQUEST){
            if(resultCode == RESULT_OK){
                Place place = PlacePicker.getPlace(this,data);
                Toast.makeText(this, place.getAddress().toString(), Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Place place = PlacePicker.getPlace(this,data);
                Toast.makeText(this, place.getAddress().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void searchPlaces(View view) {
        try {
            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                    .setCountry("BD")
                    .build();
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .setFilter(typeFilter)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }


}


