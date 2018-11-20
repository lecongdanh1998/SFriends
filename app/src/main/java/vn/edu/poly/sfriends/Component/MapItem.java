package vn.edu.poly.sfriends.Component;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class MapItem implements ClusterItem {
    private final LatLng mPosition;
    private String mTitle;
    private String mSnippet;

    public MapItem(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    public MapItem(double lat, double lng, String title, String snippet) {
        this.mPosition = new LatLng(lat, lng);
        this.mTitle = title;
        this.mSnippet = snippet;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return this.mTitle;
    }

    @Override
    public String getSnippet() {
        return this.mSnippet;
    }
}