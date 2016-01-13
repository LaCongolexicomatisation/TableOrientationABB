package fr.lpteprow.abb.tableorientationabb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAngle extends Fragment {

    View view;
    EditText editTextAngle = null;
    Button buttonValAngle = null;

    public FragmentAngle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_angle, container, false);

        editTextAngle = (EditText) view.findViewById(R.id.etAngle);


        return view;
    }

}
