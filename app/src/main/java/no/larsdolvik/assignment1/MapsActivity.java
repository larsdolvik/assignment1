package no.larsdolvik.assignment1;

import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap;      // Might be null if Google Play services APK is not available.
    private XMLhandler object;   //handles the xml page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    //gets current location and and zooms into it, also adds marker and
    //runs the function which gets data from the xml
    private void setUpMap() {

        //gets LM obj from System Service LOCATION_SERVICE
        LocationManager locMan = (LocationManager) getSystemService(LOCATION_SERVICE);

        //gets current loc(location)
        Location loc = locMan.getLastKnownLocation("network");

        //creates a latitude and longitude for current pos
        // zooms in on it and adds marker
        LatLng latiLong = new LatLng(loc.getLatitude(), loc.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latiLong, 15));
        mMap.addMarker(new MarkerOptions().position(new LatLng(loc.getLatitude(),loc.getLongitude())).title("Your position!"));

        //takes latitude and longitude as parameter
        showCity(latiLong);
    }
        //fetches xml file from the url with current latitude and longitude
        //gets data from url and displays it
    public void showCity(LatLng latiLong){
        TextView cityText = (TextView) findViewById(R.id.textViewCity);

    String url = "http://api.wunderground.com/auto/wui/geo/GeoLookupXML/index.xml?query=" + latiLong.latitude + "," + latiLong.longitude;

        object = new XMLhandler(url);
        object.fetchXML();
        while(object.parsingComplete);
        cityText.setText("You are currently in: " + object.getCity() + ". Here the timezone is: " + object.getTimeZone());
    }
}


