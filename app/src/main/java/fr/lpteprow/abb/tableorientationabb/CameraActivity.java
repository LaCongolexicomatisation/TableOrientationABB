package fr.lpteprow.abb.tableorientationabb;

/**
 * Created by Anthony on 13/01/2016.
 */
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class CameraActivity extends Activity {

    ImageView imageView;
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
        imageView.setX(-400);
    }

}