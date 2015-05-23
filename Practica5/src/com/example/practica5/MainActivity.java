package com.example.practica5;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;


public class MainActivity extends ActionBarActivity {
Button btnShowLocation;
	GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {		

		        gps = new GPSTracker(MainActivity.this);

	
		        if(gps.canGetLocation()){
		        	
		        	double latitude = gps.getLatitude();
		        	double longitude = gps.getLongitude();
		        	
		        	Toast.makeText(getApplicationContext(), "Tu localisacion es - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
		        	TextView latitude2 = (TextView) findViewById(R.id.txtLatitud);
			        TextView longitude2 = (TextView) findViewById(R.id.txtLongitud);
			        latitude2.setText(Double.toString(gps.getLatitude()));
			        longitude2.setText(Double.toString(gps.getLongitude()));
		        }else{
		        	gps.showSettingsAlert();
		        }
				
			}
		});
        
        final LocationListener mLocationListener = new LocationListener() {
		    @Override
		    public void onLocationChanged(final Location location) {
		        TextView latitud = (TextView) findViewById(R.id.txtLatitud);
		        TextView longitud = (TextView) findViewById(R.id.txtLongitud);
		        latitud.setText(Double.toString(location.getLatitude()));
		        longitud.setText(Double.toString(location.getLongitude()));
		    }

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
		};
			
		
	    LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

	    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100,
	            1, mLocationListener);

    }

	public void cargarMapa(View view){
        TextView txtLatitud = (TextView) findViewById(R.id.txtLatitud);
        TextView txtLongitud = (TextView) findViewById(R.id.txtLongitud);
        String latitud = txtLatitud.getText().toString();
        String longitud =txtLongitud.getText().toString();
		WebView webView = (WebView) findViewById(R.id.webView);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setSupportZoom(true);
		webView.setWebViewClient(new WebViewClient());
		webView.loadUrl("https://www.google.com.mx/maps/?q="+latitud+","+longitud);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
