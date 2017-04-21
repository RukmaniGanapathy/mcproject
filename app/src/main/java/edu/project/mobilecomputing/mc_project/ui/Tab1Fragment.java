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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.project.mobilecomputing.mc_project.R;
import edu.project.mobilecomputing.mc_project.model.Grocery;
import edu.project.mobilecomputing.mc_project.service.ApplicationService;
import edu.project.mobilecomputing.mc_project.service.ApplicationServiceImpl;

import static edu.project.mobilecomputing.mc_project.R.id.text;

/**
 * Created by Rukmani on 3/28/17.
 */
public class Tab1Fragment extends Fragment {
    ApplicationServiceImpl myService = new ApplicationServiceImpl();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();
    public String groceryItems= "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);
//        ListView textView = (ListView) rootView.findViewById(R.id.listView);
//        textView.a
        final EditText items = (EditText) rootView.findViewById(R.id.etitems);
        Button save = (Button) rootView.findViewById(R.id.bsave);

        //Starting of Get Grocery List
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        //TODO:
        String userId = myService.getCurrentUser().getUserId();

        databaseReference.child("grocery").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children){
                    if(child.getKey()!= null){
                        if(child.getKey().equals("items")){
                            groceryItems = child.getValue(String.class).toString();
                            System.out.println(text);
                            items.setText(groceryItems);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
        //EndOf Get Grocery List

//        String text = "apple#banana, milk";//TODO connect to db and set text from db
//        String newText = text.replace('#','\n');
        String newText = "";
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
