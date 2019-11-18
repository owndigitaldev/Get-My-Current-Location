package id.owndigitaldev.getlocation;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private TextView localityString, name, subLocality, country, region_code, zipcode, state, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localityString = findViewById(R.id.localityString);
        name = findViewById(R.id.name);
        subLocality = findViewById(R.id.subLocality);
        country = findViewById(R.id.country);
        region_code = findViewById(R.id.region_code);
        zipcode = findViewById(R.id.zipcode);
        state = findViewById(R.id.state);
        alamat = findViewById(R.id.alamat);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        Geocoder geocoder = new Geocoder(this);

        try {
            List<Address>addresses = geocoder.getFromLocation(latitude,longitude,1);
            if (geocoder.isPresent()) {
                if (addresses.size()>0) {
                    Address returnAddress = addresses.get(0);

                    localityString.setText(returnAddress.getLocality());
                    name.setText(returnAddress.getSubAdminArea());
                    subLocality.setText(returnAddress.getSubLocality());
                    country.setText(returnAddress.getCountryName());
                    region_code.setText(returnAddress.getCountryCode());
                    zipcode.setText(returnAddress.getPostalCode());
                    state.setText(returnAddress.getAdminArea());
                    alamat.setText(returnAddress.getAddressLine(0));

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
