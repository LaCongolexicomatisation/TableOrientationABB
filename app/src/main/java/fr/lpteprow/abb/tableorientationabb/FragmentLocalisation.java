package fr.lpteprow.abb.tableorientationabb;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLocalisation extends Fragment {

    TextView textViewOrientation = null;
    OrientationPrecise mOrientationPrecise = null;
    BroadcastReceiver br_orientation = null;
    String orientation ="";

    public FragmentLocalisation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_localisation, container, false);

        //localisation
        textViewOrientation = (TextView) view.findViewById(R.id.tvOrientation);


        mOrientationPrecise = OrientationPrecise.getOrientationPrecise(getActivity());

        br_orientation = new BroadcastReceiver() {
            String pattern = "##.##";
            DecimalFormat decimalFormat = new DecimalFormat(pattern);

            public void onReceive(Context context, Intent intent) {
                orientation = "Azimuth: " + decimalFormat.format(intent.getFloatExtra(OrientationPrecise.CLEF_ORIENTATION_0, -9));
                orientation += " Pitch: " + decimalFormat.format(intent.getFloatExtra(OrientationPrecise.CLEF_ORIENTATION_1, -9));
                orientation += " Roll: " + decimalFormat.format(intent.getFloatExtra(OrientationPrecise.CLEF_ORIENTATION_2, -9));
                updateUI();
            }

            private void updateUI() {
                if (orientation != "") {
                    textViewOrientation.setText(orientation);
                }
            }
        };

        return view;
    }

    public void onPause() {
        super.onPause();
        mOrientationPrecise.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(br_orientation, new IntentFilter(OrientationPrecise.MESSAGE_ORIENTATION));
        mOrientationPrecise.start();
    }
//
//    @Override
//    public void onStop(){
//        super.onStop();
//        unregisterReceiver(br_orientation);
//    }



}
