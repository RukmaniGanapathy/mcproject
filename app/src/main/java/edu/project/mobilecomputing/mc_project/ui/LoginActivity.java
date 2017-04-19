package edu.project.mobilecomputing.mc_project.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edu.project.mobilecomputing.mc_project.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private String email, password;
    Button btnRegister, btnLogin;
    EditText editTextEmail, editTextPassword;
    ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        editTextEmail = ((EditText) findViewById(R.id.email));
        editTextPassword = ((EditText) findViewById(R.id.password));
        progressDialog = new ProgressDialog(this);
        btnLogin = (Button) findViewById(R.id.login);
        btnLogin.setOnClickListener(this);
        btnRegister = (Button) findViewById(R.id.register);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnLogin){
            validateUser();
        }else if(v == btnRegister)
        {
            //redirect to the register page
            Intent intent = new Intent(v.getContext(), RegisterActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    private boolean validateUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                            //redirect page to the welcome/profile screen
                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                            startActivityForResult(intent, 0);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "There is a problem, try again...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return false;
    }
}
