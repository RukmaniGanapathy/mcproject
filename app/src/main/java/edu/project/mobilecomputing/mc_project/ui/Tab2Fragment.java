package edu.project.mobilecomputing.mc_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import edu.project.mobilecomputing.mc_project.R;
import edu.project.mobilecomputing.mc_project.model.User;
import edu.project.mobilecomputing.mc_project.service.ApplicationService;
import edu.project.mobilecomputing.mc_project.service.ApplicationServiceImpl;

/**
 * Created by Rukmani on 3/28/17.
 */
public class Tab2Fragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    Button Addfriend, showgroc;
    Button Delfriend;
    ToggleButton Notifications;
    EditText enterfriend;
    String friendlist;
    String usname = "hi";
    ApplicationService service = new ApplicationServiceImpl();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);
//        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//        textView.setText("tab2");

        Addfriend = (Button) rootView.findViewById(R.id.addfriend);
        Delfriend = (Button) rootView.findViewById(R.id.delfriend);
        showgroc = (Button) rootView.findViewById(R.id.showgroc);

        enterfriend = (EditText) rootView.findViewById(R.id.editText);
        Notifications = (ToggleButton)rootView.findViewById(R.id.notification);

        usname = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        usname = usname.replace(".","~");
        usname = usname.replace("@","%");
        System.out.println("Username:"+usname);
        final String REG_TOKEN = "REG_TOKEN";




        myRef.child("friends").child(usname).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                friendlist = snapshot.getValue(String.class);
                System.out.println("FriendList"+friendlist);
            }
            //prints "Do you have data? You'll love Firebase."

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });



        Addfriend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String recent_token= FirebaseInstanceId.getInstance().getToken();
                Log.d(REG_TOKEN, recent_token);
                FirebaseMessaging.getInstance().subscribeToTopic("user_"+usname);
                System.out.println("Add friend");
                String value = friendlist + enterfriend.getText().toString()+"#";
                myRef.child("friends").child(usname).setValue(value);


            }
        });

        showgroc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MapsActivity.class);
                startActivity(intent);


            }
        });


        Delfriend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                FirebaseMessaging.getInstance().unsubscribeFromTopic("user_"+usname);

                String deluser = enterfriend.getText().toString();
                if(!friendlist.contains(deluser))
                {
                    Toast.makeText(getActivity().getApplicationContext() , "User not in friend list!",
                            Toast.LENGTH_LONG).show();

                }
                else {
                    friendlist = friendlist.replace(deluser + "#", "");
                    myRef.child("friends").child(usname).setValue(friendlist);
                }

            }
        });

        Notifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseMessaging.getInstance().subscribeToTopic("user_"+usname);
                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("user_"+usname);
                }
            }
        });

        return rootView;
    }

}
