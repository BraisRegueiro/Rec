package com.example.rec;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment  extends Fragment {

    private EditText email, password;
    private FirebaseAuth mAuth;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frg_login, container, false);
        email = v.findViewById(R.id.frg_login_txt_email);
        password = v.findViewById(R.id.frg_login_txt_passsword);
        Button btnSignIn = v.findViewById(R.id.frg_login_btn_entrar);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkDatos();

            }
        });
        Button btnRecuperaPass = v.findViewById(R.id.frg_login_btn_olvido);
        btnRecuperaPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //     RecuperaPassFragment frgRecuperaPass = new RecuperaPassFragment();
                //    ((InicialActivity)getActivity()).changeFragmentInicio(frgRecuperaPass);
            }
        });
        return v;
    }
    private void checkDatos(){
            String error = getString(R.string.datos_incorrectos);
            Toast toast = Toast.makeText(getContext(), error,Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP,0,0);

            String compruebaEmail = email.getText().toString();
            if(compruebaEmail.trim().isEmpty()){
                toast.show();
                return;
            }
        String compruebaPassword = password.getText().toString();
        if(compruebaPassword.trim().isEmpty()){
            toast.show();
            return;
        }

        inicioSesion(compruebaEmail, compruebaPassword);
    }

    private void inicioSesion(final String email, final String password){

        final String fallo = getString(R.string.datos_incorrectos);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("OK", "Sesion iniciada");
                            FirebaseUser user = mAuth.getCurrentUser();
                            shared(user.getUid());
                            MainActivity actMain = new MainActivity();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("ERROR", "No se pudo iniciar sesion");
                            Toast toast = Toast.makeText(getContext(), fallo,Toast.LENGTH_SHORT);
                            toast.show();;

                            // ...
                        }

                        // ...
                    }
                });
    }
    private void updateUI(FirebaseUser currentUser) {
        Log.i("User:",""+currentUser);
    }

    private void shared(String id){

        String filename = "ficheroConfiguracion";
        Context ctx = getContext();
        SharedPreferences sharedPref = ctx.getSharedPreferences(filename, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("id", id);
        editor.commit();

    }

}
