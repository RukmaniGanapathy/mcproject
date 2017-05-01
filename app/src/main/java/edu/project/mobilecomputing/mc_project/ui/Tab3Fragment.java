package edu.project.mobilecomputing.mc_project.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import edu.project.mobilecomputing.mc_project.R;

/**
 * Created by Rukmani on 3/28/17.
 */
public class Tab3Fragment extends Fragment {

    Button addexpense, viewExpense;
    DatabaseReference dbRef;
    TextView tf, tf1;
    String youAreOwed = "", youOwe = "", userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3, container, false);

        addexpense = (Button) rootView.findViewById(R.id.addexpense);
        viewExpense = (Button) rootView.findViewById(R.id.viewexpense);

        addexpense.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ExpenseActivity.class);
                startActivity(intent);
            }
        });

        dbRef = FirebaseDatabase.getInstance().getReference();
        tf = (TextView) rootView.findViewById(R.id.youAreOwed);
        tf.setTypeface(null, Typeface.BOLD);

        tf1 = (TextView) rootView.findViewById(R.id.youOwe);
        tf1.setTypeface(null, Typeface.BOLD);
        userId = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        userId = userId.replace(".","~");
        userId = userId.replace("@","%");
//        System.out.println(fbUser.getUid());

        dbRef.child("expense").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    if (child.getKey() != null) {
                        if (child.getKey().equals(userId)) {
                            youAreOwed = child.getValue(String.class).toString();
                        }
                    }
                    if (child.getValue(String.class) != null) {
                        int index = child.getValue(String.class).toString().indexOf(userId);
                        if(index!=-1){
                            String temp = child.getValue(String.class).toString().substring(index+userId.length()+1);
                            temp = temp.substring(0,temp.indexOf("#"));
                            youOwe+= child.getKey().toString()+"*"+temp;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        viewExpense.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                System.out.println("boooooo  "+youAreOwed+" "+youOwe);
                if(youAreOwed != null) {
                    String[] itemsArray = youAreOwed.split("#");
                    Map<String, Integer> itemsMap = new HashMap<String, Integer>();
                    for (String item : itemsArray) {
                        String[] transaction = item.split("\\*");
                        String email = transaction[0].replace("~", ".");
                        email = email.replace("%", "@");
                        if (itemsMap.containsKey(email)) {
                            int amount = itemsMap.get(email) + Integer.parseInt(transaction[2]);
                            itemsMap.put(email, amount);
                        } else {
                            itemsMap.put(email, Integer.parseInt(transaction[2]));
                        }
                    }
                    String transactions = "";
                    for (String key : itemsMap.keySet()) {
                        transactions += key + "\t" + itemsMap.get(key) + "\n";
                    }
                    tf.setText("The following people owe you \n" + transactions);
                }
                if(youOwe != null) {
                    String[] itemsArray1 = youOwe.split("#");
                    Map<String, Integer> itemsMap1 = new HashMap<String, Integer>();
                    for (String item : itemsArray1) {
                        String[] transaction = item.split("\\*");
                        String email = transaction[0].replace("~", ".");
                        email = email.replace("%", "@");
                        if (itemsMap1.containsKey(email)) {
                            int amount = itemsMap1.get(email) + Integer.parseInt(transaction[2]);
                            itemsMap1.put(email, amount);
                        } else {
                            itemsMap1.put(email, Integer.parseInt(transaction[2]));
                        }
                    }
                    String transactions1 = "";
                    for (String key : itemsMap1.keySet()) {
                        transactions1 += key + "\t" + itemsMap1.get(key) + "\n";
                    }
                    tf1.setText("You owe the following people \n" + transactions1);
                }
            }
        });

        return rootView;
    }
}
