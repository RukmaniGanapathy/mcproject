package edu.project.mobilecomputing.mc_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.project.mobilecomputing.mc_project.R;
import edu.project.mobilecomputing.mc_project.model.Grocery;
import edu.project.mobilecomputing.mc_project.service.ApplicationService;
import edu.project.mobilecomputing.mc_project.service.ApplicationServiceImpl;

import static edu.project.mobilecomputing.mc_project.R.id.text;

/**
 * Created by Rukmani on 3/28/17.
 */
public class Tab1Fragment extends Fragment {
    ApplicationService myService = new ApplicationServiceImpl();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();
    public String groceryItems= "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);
        final EditText items = (EditText) rootView.findViewById(R.id.etitems);
        Button save = (Button) rootView.findViewById(R.id.bsave);
        Button send = (Button) rootView.findViewById(R.id.sendlist);

        //Starting of Get Grocery List
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        final String userId = myService.getCurrentUser().getUserId();

        databaseReference.child("grocery").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children){
                    if(child.getKey()!= null){
                        if(child.getKey().equals("items")){
                            groceryItems = child.getValue(String.class).toString();
                            String newText = groceryItems.replace('#','\n');
                            System.out.println(text);
                            items.setText(newText);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
        //EndOf Get Grocery List

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = items.getText().toString();
                String saveToDb = item.replace('\n', '#');
                Grocery grocery = new Grocery();
                grocery.setItems(saveToDb);
                grocery.setUserId(userId);

                FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseDatabase.getInstance().getReference().child("grocery").child(fbUser.getUid()).setValue(grocery);

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
                String item = items.getText().toString();
                item = item.replace('\n', '#');
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"mcspring2017@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Grocery List");
                i.putExtra(Intent.EXTRA_TEXT   , item);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    //Toast.makeText(Tab1Fragment.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }

}
