package edu.project.mobilecomputing.mc_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import edu.project.mobilecomputing.mc_project.R;
import edu.project.mobilecomputing.mc_project.service.ApplicationServiceImpl;

/**
 * Created by Rukmani on 3/28/17.
 */
public class Tab3Fragment extends Fragment {

    ApplicationServiceImpl myService = new ApplicationServiceImpl();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();
    public String userId= "";
    EditText editTextFriendName, editTextItemName, editTextAmount;
    Button addexpense;
    String myExpense = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText("tab3");

        addexpense = (Button) rootView.findViewById(R.id.addexpense);

        addexpense.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ExpenseActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
