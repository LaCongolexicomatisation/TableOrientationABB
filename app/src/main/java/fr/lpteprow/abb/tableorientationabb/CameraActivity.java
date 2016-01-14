package fr.lpteprow.abb.tableorientationabb;

/**
 * Created by Anthony on 13/01/2016.
 */
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CameraActivity extends Activity {

    ImageView imageView;
    TextView textViewOrientation = null;
    OrientationPrecise mOrientationPrecise = null;
    BroadcastReceiver br_orientation = null;
    String orientation ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }
        imageView = (ImageView) findViewById(R.id.arrow);
        imageView.setX(200);
        textViewOrientation = (TextView)findViewById(R.id.textViewPosition);
        textViewOrientation.setX(250);
        mOrientationPrecise = OrientationPrecise.getOrientationPrecise(this);
        br_orientation = new BroadcastReceiver() {
            String pattern = "##.##";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);
            public void onReceive(Context context, Intent intent) {
                String orientation = "Azimuth: " + decimalFormat.format(intent.getFloatExtra(OrientationPrecise.CLEF_ORIENTATION_0, -9));
                orientation += " Pitch: " + decimalFormat.format(intent.getFloatExtra(OrientationPrecise.CLEF_ORIENTATION_1, -9));
                orientation += " Roll: " + decimalFormat.format(intent.getFloatExtra(OrientationPrecise.CLEF_ORIENTATION_2, -9));
                textViewOrientation.setText(orientation);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(br_orientation, new IntentFilter(OrientationPrecise.MESSAGE_ORIENTATION));
        mOrientationPrecise.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mOrientationPrecise.stop();
    }

}