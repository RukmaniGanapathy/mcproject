package edu.project.mobilecomputing.mc_project.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.project.mobilecomputing.mc_project.R;
import edu.project.mobilecomputing.mc_project.model.User;
import edu.project.mobilecomputing.mc_project.service.ApplicationService;
import edu.project.mobilecomputing.mc_project.service.ApplicationServiceImpl;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextName, editTextEmail, editTextPassword, editTextConfirmPassword;
    Button btnRegister;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private User myUser = new User();
    private final ApplicationService service = new ApplicationServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        editTextName = ((EditText) findViewById(R.id.name));
        editTextEmail = ((EditText) findViewById(R.id.email));
        editTextPassword = ((EditText) findViewById(R.id.password));
        editTextConfirmPassword = ((EditText) findViewById(R.id.confirmPassword));

        btnRegister = (Button) findViewById(R.id.register);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == btnRegister )
        {
            addUser();
        }
    }

    private void addUser(){
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String password2 = editTextConfirmPassword.getText().toString().trim();

        //progressDialog.setMessage("Registering user...");
        //progressDialog.show();

        if(checkPassword(password, password2)) {
            if (!TextUtils.isEmpty(name)) {
                //String id = dbUsers.push().getKey();
                //User user = new User(id, name, email, password);
                //dbUsers.child(id).setValue(user);

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                                if(task.isSuccessful()){
                                    SaveUserInformation();
                                    Toast.makeText(RegisterActivity.this, "Register successful..", Toast.LENGTH_SHORT).show();

                                    //redirect page to the welcome/profile screen
                                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                    startActivityForResult(intent, 0);
                                }
                                else{
                                    System.out.println(task.getException().toString());
                                    System.out.println(task.getResult().toString());

                                    Toast.makeText(RegisterActivity.this, "There is a problem, try again..", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Passwords entered doesn't match", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkPassword(String psw1, String psw2){
        //both are equal
        //check length
        int length = psw1.length();

        if(length >= 5) {
            if (psw1.equals(psw2)) {
                return true;
            } else {
                return false;
            }
        }else{
            Toast.makeText(this, "Password should be at least 6 character", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void encryptPassword(){
        //encrypt password
    }

    private void SaveUserInformation(){
        String name =editTextName.getText().toString().trim();
        String email =editTextEmail.getText().toString().trim();
        String psw = editTextPassword.getText().toString().trim();

        FirebaseUser fbUser = firebaseAuth.getCurrentUser();
        myUser.setUserId(fbUser.getUid());
        myUser.setEmail(fbUser.getEmail());
        myUser.setName(name);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("users").child(fbUser.getUid()).setValue(myUser);
        String friendkeyemail = fbUser.getEmail();
        friendkeyemail = friendkeyemail.replace(".","~");
        friendkeyemail = friendkeyemail.replace("@","%");
        databaseReference.child("friends").child(friendkeyemail).setValue("#");
        service.setMyUser(myUser);



        Toast.makeText(this, "User saved!...", Toast.LENGTH_SHORT).show();
//

    }
//
//    public static User getMyUser() {
//        return myUser;
//    }
}
