package edu.project.mobilecomputing.mc_project.ui;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import edu.project.mobilecomputing.mc_project.R;

/**
 * Created by Rukmani on 3/28/17.
 */
public class Tab1Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);
//        ListView textView = (ListView) rootView.findViewById(R.id.listView);
//        textView.a
        final EditText items = (EditText) rootView.findViewById(R.id.etitems);
        Button save = (Button) rootView.findViewById(R.id.bsave);

        String text = "apple#banana, milk";//TODO connect to db and set text from db
        String newText = text.replace('#','\n');
        items.setText(newText);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = items.getText().toString();
                String saveToDb = item.replace('\n', '#');
                //String[] itemsList = item.split("\n");

                Toast.makeText(getActivity().getApplicationContext(),saveToDb,Toast.LENGTH_LONG).show();
                //TODO save items to db
            }
        });

        return rootView;
    }
}
