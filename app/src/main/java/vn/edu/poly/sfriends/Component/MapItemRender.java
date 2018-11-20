package vn.edu.poly.sfriends.Component;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.res.ResourcesCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class MapItemRender extends DefaultClusterRenderer<MapItem> {

    private final Context context;

    public MapItemRender(Context context, GoogleMap map, ClusterManager<MapItem> clusterManager) {
        super(context, map, clusterManager);
        this.context = context;
    }

    @Override
    protected void onBeforeClusterItemRendered(MapItem item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<MapItem> cluster, MarkerOptions markerOptions) {
        super.onBeforeClusterRendered(cluster, markerOptions);
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster<MapItem> cluster) {
        return cluster.getSize() > 5;
    }

    private BitmapDescriptor getBitmapDescriptor(@DrawableRes int id) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(context.getResources(), id, null);
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
