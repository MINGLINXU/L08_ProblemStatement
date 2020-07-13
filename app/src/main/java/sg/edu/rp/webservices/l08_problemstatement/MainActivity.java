package sg.edu.rp.webservices.l08_problemstatement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3;
    private GoogleMap map;
    Marker north, east, central;
    LatLng poi_central, poi_east, poi_north;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng singapore = new LatLng(1.352083, 103.819839);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore, 10));
                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

                if(permissionCheck == PermissionChecker.PERMISSION_GRANTED){
                    map.setMyLocationEnabled(true);
                }else{
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }
                poi_central = new LatLng(1.288588, 103.860342);
                central = map.addMarker(new MarkerOptions().position(poi_central).title("Central").snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                        "Operating hours: 11am-8pm\n" +
                        "Tel:67788652\n").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                poi_east = new LatLng(1.328815, 103.943051);
                east = map.addMarker(new MarkerOptions().position(poi_east).title("East").snippet("Block 555, Tampines Ave 3, 287788 \n" +
                        "Operating hours: 9am-5pm\n" + "Tel:66776677\n").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                poi_north = new LatLng(1.44224, 103.785733);
                north = map.addMarker(new MarkerOptions().position(poi_north).title("HQ-North").snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm\n" +
                        "Tel:65433456\n").icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_big_on)));


            }
        });

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,15));
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,15));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east,15));
                }
            }
        });

    }
}