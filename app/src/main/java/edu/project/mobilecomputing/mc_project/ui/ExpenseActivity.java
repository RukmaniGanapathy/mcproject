package edu.project.mobilecomputing.mc_project.ui;

//import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.project.mobilecomputing.mc_project.R;
import edu.project.mobilecomputing.mc_project.service.ApplicationServiceImpl;

import static edu.project.mobilecomputing.mc_project.R.id.text;

public class ExpenseActivity  extends AppCompatActivity implements View.OnClickListener{
    ApplicationServiceImpl myService = new ApplicationServiceImpl();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();
    public String userId= "";
    EditText editTextFriendName, editTextItemName, editTextAmount;
    Button btnAdd;
    String myExpense = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        userId = myService.getCurrentUser().getUserId();
        editTextFriendName = ((EditText) findViewById(R.id.friendName));
        editTextItemName = ((EditText) findViewById(R.id.itemName));
        editTextAmount = ((EditText) findViewById(R.id.amount));

        btnAdd = (Button) findViewById(R.id.add);
        btnAdd.setOnClickListener(this);

        databaseReference.child("expense").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children){
                    if(child.getKey()!= null){
                        if(child.getKey().equals(userId)){
                            myExpense = child.getValue(String.class).toString();
                            System.out.println(myExpense);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

        //
       // return rootView;

    }

    @Override
    public void onClick(View v) {
        if(v == btnAdd){
            AddExpense();
        }
    }

    private void AddExpense(){
        String friendName =editTextFriendName.getText().toString().trim();
        String itemName =editTextItemName.getText().toString().trim();
        String amount = editTextAmount.getText().toString().trim();

        myExpense += friendName + "*" + itemName + "*" + amount + "#";

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();

        databaseReference.child("expense").child(userId).setValue(myExpense);

       Toast.makeText(ExpenseActivity.this,"Expense is added", Toast.LENGTH_SHORT).show();

        editTextFriendName.setText("");
        editTextItemName.setText("");
        editTextAmount.setText("");

}


}
