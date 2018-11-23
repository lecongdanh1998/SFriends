package vn.edu.poly.sfriends.View.HomePage.RealEstate;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.edu.poly.sfriends.Adapter.CustomInfoMarkerAdapter;
import vn.edu.poly.sfriends.Adapter.MapDirectionAdapter;
import vn.edu.poly.sfriends.Adapter.ResultSearchAdapter;
import vn.edu.poly.sfriends.Component.MapItem;
import vn.edu.poly.sfriends.Component.MapItemRender;
import vn.edu.poly.sfriends.Model.MapDirectionModel;
import vn.edu.poly.sfriends.Model.ResultSearchModel;
import vn.edu.poly.sfriends.R;
import vn.edu.poly.sfriends.Server.ApiConnect;

import static vn.edu.poly.sfriends.Component.BaseActivity.dataLoginUser;

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
    private String TAG = "SEARCH";
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
    private View view_list, view_directions;
    private int flag = 0;
    private BottomSheetBehavior bottomSheetBehavior;
    private String URL_CONNECT = ApiConnect.URL_GET_NEAR_PLACE("http://data.timzita.com");
    private String token;
    private CardView layout_search;
    private String geo;
    private boolean direction = false;
    private TextView txt_direction;
    private TextView txt_time_direction_details, txt_km_direction_details,
            txt_from_location_details, txt_to_location_details, title_sheet_details;
    private ListView listView_map_direction_details;
    private ArrayList<MapDirectionModel> listMapDirection;
    private MapDirectionAdapter mapDirectionAdapter;
    private GoogleApiClient googleApiClient;
    final static int REQUEST_LOCATION = 199;


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
        layout_search = findViewById(R.id.layout_search);
        txt_direction = findViewById(R.id.txt_direction);
        title_sheet_details = findViewById(R.id.title_sheet_details);
        txt_time_direction_details = findViewById(R.id.txt_time_direction_details);
        txt_km_direction_details = findViewById(R.id.txt_km_direction_details);
        txt_from_location_details = findViewById(R.id.txt_from_location_details);
        txt_to_location_details = findViewById(R.id.txt_to_location_details);
        listView_map_direction_details = findViewById(R.id.listView_map_direction_details);
        try {
            geo = getIntent().getStringExtra("direction");
            if (geo.length() > 0) {
                direction = true;
                edt_search.setVisibility(View.GONE);
                view_list = findViewById(R.id.layout_list_item);
                view_list.setVisibility(View.GONE);
                view_directions = findViewById(R.id.layout_direction);
                view_directions.setVisibility(View.VISIBLE);
                bottomSheetBehavior = BottomSheetBehavior.from(view_directions);
            } else {
                view_list = findViewById(R.id.layout_list_item);
                bottomSheetBehavior = BottomSheetBehavior.from(view_list);
            }
            Toast.makeText(this, "" + geo, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            view_list = findViewById(R.id.layout_list_item);
            bottomSheetBehavior = BottomSheetBehavior.from(view_list);
        }
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_DRAGGING ||
                        bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                    title_sheet_details.setVisibility(View.GONE);
                } else if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){
                    title_sheet_details.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
    }

    private void initEventButton() {
        edt_search.addTextChangedListener(this);
        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context
                            .INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edt_search.getApplicationWindowToken(), 0);
                }
                return handled;
            }
        });
    }

    private void initData() {
        listResult = new ArrayList<>();
        searchAdapter = new ResultSearchAdapter(this, listResult);
        listView_search.setAdapter(searchAdapter);
        listView_search.setOnItemClickListener(this);
    }

    private void getDataNearPlace(final String id_type) {
        dialog.show();
        token = dataLoginUser.getString("usertoken", "");
        listMapItem = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CONNECT, new
                Response
                        .Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray data = jsonObject.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject object = data.getJSONObject(i);
                                String id = object.getString("id");
                                String title = object.getString("title");
                                String slug = object.getString("slug");
                                String description = object.getString("description");
                                String img = object.getString("img");
                                String phone = object.getString("phone");
                                String email = object.getString("email");
                                String website = object.getString("website");
                                String facebook = object.getString("facebook");
                                String geolocation2 = object.getString("geolocation");
                                String address = object.getString("address");
                                String add_ward = object.getString("add_ward");
                                String add_city = object.getString("add_city");
                                String link = object.getString("link");
                                String type_id = object.getString("type_id");
                                String views = object.getString("view");

                                listResult.add(new ResultSearchModel(
                                        id,
                                        title,
                                        slug,
                                        description,
                                        img,
                                        phone,
                                        email,
                                        website,
                                        facebook,
                                        geolocation2,
                                        address,
                                        add_ward,
                                        add_city,
                                        link,
                                        type_id,
                                        views
                                ));
                            }
                        } catch (Exception e) {
                            dialog.dismiss();
                            Toast.makeText(SearchMap.this, "" + e, Toast.LENGTH_SHORT).show();
                        }
                        searchAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(SearchMap.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> paramsHeader = new HashMap<>();
                paramsHeader.put("Authorization", token);
                return paramsHeader;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> paramsBody = new HashMap<>();
                paramsBody.put("type_id", id_type);
                return paramsBody;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
//        addClutter(listResult);
    }

    private void addClutter(ArrayList<ResultSearchModel> listResult) {
        for (int i = 0; i < listResult.size(); i++) {
            JSONObject dataSnippet = new JSONObject();
            try {
                dataSnippet.put("description", listResult.get(i).getDescription());
                dataSnippet.put("price", "đ1,607,619 Per night - Free cancellation");
                dataSnippet.put("rating", "80");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String snippet = dataSnippet.toString();

            double lat = Double.valueOf(listResult.get(i).getGeolocation().substring(0,
                    listResult.get(i)
                            .getGeolocation().indexOf(",")));
            double lng = Double.valueOf(listResult.get(i).getGeolocation().substring(listResult
                            .get(i)
                            .getGeolocation().indexOf(","),
                    listResult.get(i).getGeolocation().length()).replace(",", ""));
            listMapItem.add(new MapItem(lat, lng, listResult.get(i).getTitle(), snippet));
        }
        add(listMapItem);
        builder = new LatLngBounds.Builder();
        builder.include(SphericalUtil.computeOffset(circle.getCenter(), circle.getRadius() * Math
                .sqrt(2), 45));
        builder.include(SphericalUtil.computeOffset(circle.getCenter(), circle.getRadius() * Math
                .sqrt(2), 225));
        bounds = builder.build();
    }

    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            isService();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
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
//            getDeviceLocation();
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
        getDataNearPlace("3");
        if (direction == true) {
            if (circle != null) {
                circle.remove();
            }
            double lat = Double.valueOf(geo.substring(0, geo.indexOf(",")));
            double lng = Double.valueOf(geo.substring(geo.indexOf(","), geo.length()).replace("," +
                    "", ""));
            mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).icon
                    (BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_CYAN))).setTag
                    (position);
            getDirection(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation
                    .getLongitude()), new LatLng
                    (lat, lng), "driving");
        }
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
        if (s.length() > 0) {
            ArrayList<ResultSearchModel> resultSearchModels = new ArrayList<>();
            listView_search.setAdapter(null);
            for (int i = 0; i < listResult.size(); i++) {
                if (listResult.get(i).getTitle().toLowerCase().contains(s.toLowerCase())) {
                    resultSearchModels.add(listResult.get(i));
                }
            }
            searchAdapter = new ResultSearchAdapter(this, resultSearchModels);
            searchAdapter.notifyDataSetChanged();
            listView_search.setAdapter(searchAdapter);
        } else {
            searchAdapter = new ResultSearchAdapter(this, listResult);
            listView_search.setAdapter(searchAdapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailsItemMap.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object", listResult.get(position));
        intent.putExtra("data", bundle);
        startActivity(intent);
        finish();
    }

    private String getRequestUrl(LatLng origin, LatLng destination, String diverMode) {
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + destination.latitude + "," + destination.longitude;
        // Sensor enabled
        String sensor = "sensor=false";
        String mode = "mode=driving";
        String language = "language=vi";
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode + "&" +
                language +
                "&key=" +
                getResources().getString(R.string.key_google_api);
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
        return url;
    }

    private void getDirection(final LatLng origin, final LatLng destination, String divermode) {
        dialog.show();
        final ArrayList<LatLng> polyLineList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getRequestUrl
                (origin, destination, divermode), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                polyLineList.clear();
                dialog.dismiss();
                try {
                    JSONArray routesArray = response.getJSONArray("routes");
                    JSONObject object = routesArray.getJSONObject(0);
                    JSONObject overviewPolylines = object.getJSONObject("overview_polyline");
                    String encodedString = overviewPolylines.getString("points");
                    List<LatLng> list = decodePoly(encodedString);
                    Polyline line = mMap.addPolyline(new PolylineOptions()
                            .addAll(list)
                            .width(5)
                            .color(getResources().getColor(R.color.colorPrimaryDark))//Google maps
                            // blue color
                            .geodesic(true)
                    );
                    JSONArray legs = object.getJSONArray("legs");
                    JSONObject objectSteps = legs.getJSONObject(0);
                    JSONArray steps = objectSteps.getJSONArray("steps");
                    setStepListview(steps, origin, destination);
                    txt_time_direction_details.setText(objectSteps.getJSONObject("duration")
                            .getString("text"));
                    txt_km_direction_details.setText(" (" + objectSteps.getJSONObject("distance")
                            .getString("text") + ")");
                    txt_from_location_details.setText(objectSteps.getString("start_address"));
                    txt_to_location_details.setText(objectSteps.getString("end_address"));
                } catch (JSONException e) {
                    Log.d(TAG, e + "");
                    e.printStackTrace();
                    dialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error: " + error);
                dialog.dismiss();
                Toast.makeText(SearchMap.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }

    private void setStepListview(JSONArray steps, LatLng origin, LatLng destination) {
        listMapDirection = new ArrayList<>();
        builder = new LatLngBounds.Builder();
        builder.include(origin);
        builder.include(destination);
        String direction = "";
        int maneuver = R.drawable.ic_arrow_upward_black_24dp;
        for (int i = 0; i < steps.length(); i++) {
            try {
                JSONObject object = steps.getJSONObject(i);
                lat = Double.parseDouble(object.getJSONObject("end_location").getString("lat"));
                lng = Double.parseDouble(object.getJSONObject("end_location").getString("lng"));
                direction = object.getString("html_instructions");
                if (object.getString("maneuver") !=null){
                    String direct = object.getString("maneuver");
                    if (direct.equals("turn-right")){
                        maneuver = R.drawable.ic_chevron_right_black_24dp;
                    } else if (direct.equals("turn-left")){
                        maneuver = R.drawable.ic_chevron_left_black_24dp;
                    } else if (direct.equals("straight")){
                        maneuver = R.drawable.ic_arrow_upward_black_24dp;
                    } else {
                        maneuver = R.drawable.ic_arrow_upward_black_24dp;
                    }
                } else {
                    maneuver = R.drawable.ic_arrow_upward_black_24dp;
                }
                builder.include(new LatLng(lat, lng));
                listMapDirection.add(new MapDirectionModel(maneuver, direction));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mapDirectionAdapter = new MapDirectionAdapter(listMapDirection, this);
        listView_map_direction_details.setAdapter(mapDirectionAdapter);
        bounds = builder.build();
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, wScreen, hScreen,
                padding));
    }

    private List<LatLng> decodePoly(String encoded) {

        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;
    }
}
