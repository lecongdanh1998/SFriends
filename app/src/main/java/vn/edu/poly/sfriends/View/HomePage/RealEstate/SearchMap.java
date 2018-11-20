package vn.edu.poly.sfriends.View.HomePage.RealEstate;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.SphericalUtil;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.sfriends.Adapter.CustomInfoMarkerAdapter;
import vn.edu.poly.sfriends.Adapter.ResultSearchAdapter;
import vn.edu.poly.sfriends.Component.MapItem;
import vn.edu.poly.sfriends.Component.MapItemRender;
import vn.edu.poly.sfriends.Model.ResultSearchModel;
import vn.edu.poly.sfriends.R;

public class SearchMap extends AppCompatActivity implements OnMapReadyCallback, GoogleMap
        .OnMapLongClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapLoadedCallback,
        View.OnClickListener, ClusterManager.OnClusterItemClickListener<MapItem>, ClusterManager
                .OnClusterItemInfoWindowClickListener<MapItem>, TextWatcher, AdapterView
                .OnItemClickListener {

    SupportMapFragment mapFragment;
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private boolean mLocationPermissionGranted;
    private final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private GoogleMap mMap;
    private Location mLastKnownLocation;
    private String TAG = "MAINACTIVITY";
    private float DEFAULT_ZOOM = 11f;
    private LatLng mDefaultLocation;
    private Circle circle;
    private int position = 1;
    private ArrayList<LatLng> latLngList;
    private Marker marker;
    private FloatingActionButton fab_1km, fab_2km, fab_3km;
    private List<MapItem> mapItems;
    private DisplayMetrics metrics;
    private String geolocation, polygon;
    private double lat, lng;
    private ProgressDialog dialog;
    private Polyline polyline1;
    private LatLngBounds.Builder builder;
    private LatLngBounds bounds;
    private int wScreen, hScreen, padding;
    private ClusterManager<MapItem> mClusterManager;
    private ArrayList<MapItem> listMapItem;
    private EditText edt_search;
    private ArrayList<ResultSearchModel> listResult;
    private ListView listView_search;
    private LinearLayout layout_title_result;
    private ResultSearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_map);
        initView();
        initEventButton();
        initData();
        getLocationPermission();
    }

    private void initView() {
        wScreen = getResources().getDisplayMetrics().widthPixels;
        hScreen = getResources().getDisplayMetrics().heightPixels;
        padding = (int) (wScreen * 0.10);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        edt_search = findViewById(R.id.edt_search);
        layout_title_result = findViewById(R.id.layout_title_result);
        listView_search = findViewById(R.id.listView_search);
        LinearLayout.LayoutParams listViewSearchLayoutParams = (LinearLayout.LayoutParams)
                listView_search.getLayoutParams();
//        listViewSearchLayoutParams.setMargins(16, layout_title_result.getHeight(), 16, 16);
//        listViewSearchLayoutParams.height = hScreen - (layout_title_result.getHeight());
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
    }

    private void initEventButton() {
        edt_search.addTextChangedListener(this);
    }

    private void initData() {
        listResult = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listResult.add(new ResultSearchModel(String.valueOf(i), getResources().getString(R
                    .string.name_result), getResources().getString(R.string.review_result),
                    getResources().getString(R.string.country_result), getResources().getString(R
                    .string.address_result), getResources().getString(R.string.time_result)));
        }
        listResult.add(new ResultSearchModel("10", "NKS", "123", "Vietnamese", "212 Le Van Si, " +
                "Phuong 4, Quan 3, TP.HCM", "09:0AM"));
        listResult.add(new ResultSearchModel("11", "NKS2", "123", "Vietnamese", "212 Le Van Si, " +
                "Phuong 4, Quan 3, TP.HCM", "09:0AM"));
        listResult.add(new ResultSearchModel("12", "NKS3", "123", "Vietnamese", "212 Le Van Si, " +
                "Phuong 4, Quan 3, TP.HCM", "09:0AM"));
        searchAdapter = new ResultSearchAdapter(this, listResult);
        listView_search.setAdapter(searchAdapter);
        listView_search.setOnItemClickListener(this);
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            isService();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    private void isService() {
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if (available == ConnectionResult.SUCCESS) {
            Toast.makeText(this, "Oke", Toast.LENGTH_SHORT).show();
            getDeviceLocation();
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, available, 1);
            dialog.show();
        } else {
            Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
        }
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);//
        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        latLngList = new ArrayList<>();
        try {
            if (mLocationPermissionGranted) {
                Task locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = (Location) task.getResult();
                            mMap.addMarker(new MarkerOptions().position(new LatLng
                                    (mLastKnownLocation.getLatitude(), mLastKnownLocation
                                            .getLongitude())).icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_CYAN))).setTag
                                    (position);
                            position++;
                            circle = mMap.addCircle(new CircleOptions()
                                    .center(new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()))
                                    .radius(1000)
                                    .strokeColor(Color.RED));
                            float currentZoomLevel = getZoomLevel(circle) + 0.5f;
//                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                                    new LatLng(mLastKnownLocation.getLatitude(),
//                                            mLastKnownLocation.getLongitude()), animateZomm));
//                            mapItems.add(new MapItem(mLastKnownLocation.getLatitude(),
//                                    mLastKnownLocation.getLatitude()));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(circle.getCenter
                                    (), currentZoomLevel));
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation,
                                    DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(true);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
//        LatLng sydney = new LatLng(-33.852, 151.211);
//        googleMap.addMarker(new MarkerOptions().position(sydney)
//                .title("Marker in Sydney"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLoadedCallback(this);
        mMap.setMinZoomPreference(-10f);
        // Do other setup activities here too, as described elsewhere in this tutorial.

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();
        setUpClusterer();
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        if (circle != null) {
            if (checkInsideRadius(latLng, circle)) {

            }
        }
        Log.d("Ahaha", latLng.latitude + "," + latLng.longitude);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return true;
    }

    // Declare a variable for the cluster manager.

    private void setUpClusterer() {

        mapItems = new ArrayList<>();
        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = new ClusterManager<MapItem>(this, mMap);
        MapItemRender itemRender = new MapItemRender(this, mMap, mClusterManager);
        mClusterManager.setRenderer(itemRender);
//        });
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        mMap.setInfoWindowAdapter(mClusterManager.getMarkerManager());
        mMap.setOnInfoWindowClickListener(mClusterManager);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);
        mClusterManager.setOnClusterItemClickListener(this);
        mMap.setBuildingsEnabled(true);
        //noinspection MissingPermission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    private void add(List<MapItem> mapItems) {
        mClusterManager.addItems(mapItems);
        mClusterManager.getMarkerCollection().setOnInfoWindowAdapter(
                new CustomInfoMarkerAdapter(SearchMap.this));
    }

    private boolean checkInsideRadius(LatLng latLng, Circle circle) {
        float[] distance = new float[2];

        Location.distanceBetween(latLng.latitude, latLng.longitude,
                circle.getCenter().latitude, circle.getCenter().longitude, distance);

        if (distance[0] > circle.getRadius()) {
            Toast.makeText(getBaseContext(), "Outside", Toast.LENGTH_LONG).show();
            return false;
        } else {
            Toast.makeText(getBaseContext(), "Inside", Toast.LENGTH_LONG).show();
            return true;
        }
    }

    @Override
    public void onMapLoaded() {
        String title = "Entire apartment - 1 bed";
        JSONObject object = new JSONObject();
        try {
            object.put("description", "champs-ElysEes Golden triangle flat with mezzanine");
            object.put("price", "đ1,607,619 Per night - Free cancellation");
            object.put("rating", "80");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String snippet = object.toString();
        listMapItem = new ArrayList<>();
        listMapItem.add(new MapItem(10.832923665065772, 106.68134819716215, title, snippet));
        listMapItem.add(new MapItem(10.832705008916149, 106.68105348944663, title, snippet));
        listMapItem.add(new MapItem(10.832848255056302, 106.68101761490107, title, snippet));
        listMapItem.add(new MapItem(10.830900760285958, 106.68092541396618, title, snippet));
        listMapItem.add(new MapItem(10.832706655423902, 106.67986191809177, title, snippet));
        listMapItem.add(new MapItem(10.831534010313277, 106.67938850820065, title, snippet));
        listMapItem.add(new MapItem(10.834166116330184, 106.67985454201698, title, snippet));
        listMapItem.add(new MapItem(10.834230000512383, 106.67833171784878, title, snippet));
        listMapItem.add(new MapItem(10.828625593524412, 106.68442469090222, title, snippet));
        listMapItem.add(new MapItem(10.828413849668426, 106.68671160936356, title, snippet));
        listMapItem.add(new MapItem(10.824690360548018, 106.6922091320157, title, snippet));
        listMapItem.add(new MapItem(10.823235793151186, 106.69683024287224, title, snippet));
        listMapItem.add(new MapItem(10.823012848871397, 106.7013293132186, title, snippet));
        listMapItem.add(new MapItem(10.829025370785326, 106.70081298798324, title, snippet));
        listMapItem.add(new MapItem(10.829927666760637, 106.69843286275864, title, snippet));
        listMapItem.add(new MapItem(10.834000478412841, 106.69678095728159, title, snippet));
        listMapItem.add(new MapItem(10.835829076425556, 106.69534362852573, title, snippet));
        add(listMapItem);
        builder = new LatLngBounds.Builder();
        builder.include(SphericalUtil.computeOffset(circle.getCenter(), circle.getRadius() * Math
                .sqrt(2), 45));
        builder.include(SphericalUtil.computeOffset(circle.getCenter(), circle.getRadius() * Math
                .sqrt(2), 225));
        bounds = builder.build();
    }

    //    draw polyline
    public void renderPolyLine(ArrayList<LatLng> latLngList) {
        if (polyline1 != null) {
            polyline1.remove();
        }
        polyline1 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .addAll(latLngList));
        polyline1.setWidth(2);
        polyline1.setColor(Color.RED);
    }

    //camera center
    private void setCenterCameraPolyLine() {
        double center = mMap.getCameraPosition().target.latitude;
        double southMap = mMap.getProjection().getVisibleRegion().latLngBounds.southwest.latitude;

        double diff = (center - southMap);

        double newLat = marker.getPosition().latitude + diff;

        CameraUpdate centerCam = CameraUpdateFactory.newLatLng(new LatLng(newLat, marker
                .getPosition().longitude));

        mMap.animateCamera(centerCam);
    }

    public void setCircleMapWithFilter(double lat, double lng, double radiusInMeters) {
        if (circle != null) {
            circle.remove();
        }
        circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(mLastKnownLocation.getLatitude(),
                        mLastKnownLocation.getLongitude()))
                .radius(radiusInMeters)
                .strokeColor(Color.RED));
        float currentZoomLevel = getZoomLevel(circle);
        float animateZomm = currentZoomLevel + 5;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), animateZomm));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(currentZoomLevel), (int) radiusInMeters,
                null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    public float getZoomLevel(Circle circle) {
        int zoomLevel = 11;
        if (circle != null) {
            double radius = circle.getRadius() + circle.getRadius() / 2;
            double scale = radius / 500;
            zoomLevel = (int) (16 - Math.log(scale) / Math.log(2));
        }
        return zoomLevel;
    }

    @Override
    public boolean onClusterItemClick(MapItem mapItem) {

        return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(MapItem mapItem) {
        //-------------------------------
        Intent i = new Intent(this, RealEstate.class);
        i.setAction(mapItem.getTitle());
        startActivity(i);

        //You may want to do different things for each InfoWindow:
        if (mapItem.getTitle().equals("some title")) {

            //do something specific to this InfoWindow....

        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        filterList(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void filterList(String s) {
        ArrayList<ResultSearchModel> resultSearchModels = new ArrayList<>();
        listView_search.setAdapter(null);
        for (int i = 0; i < listResult.size(); i++) {
            if (listResult.get(i).getName().equalsIgnoreCase(s)) {
                resultSearchModels.add(listResult.get(i));
                Toast.makeText(this, "" + listResult.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        }
        searchAdapter = new ResultSearchAdapter(this, resultSearchModels);
        searchAdapter.notifyDataSetChanged();
        listView_search.setAdapter(searchAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailsItemMap.class);
        startActivity(intent);
    }
}
