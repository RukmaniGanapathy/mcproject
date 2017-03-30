package edu.project.mobilecomputing.mc_project.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.project.mobilecomputing.mc_project.R;

/**
 * Created by Rukmani on 3/28/17.
 */
public class Tab2Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText("tab2");
        return rootView;
    }
}
