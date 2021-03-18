package com.example.rec;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();

        // mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        //user = mAuth.getCurrentUser();
        // cargaDocumentoUsuarioLogueado(user.getUid());

        String doc = idDOcUserLOgueado();
        cargaDocumentoUsuarioLogueado(doc);



    }

    private void initButtons() {
        ImageButton btnCrea = findViewById(R.id.frg_main_menu_crea);





        btnCrea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crea frgCrea = new Crea();
                changeFragmentMain(frgCrea);
            }
        });


    }

    public Usuario getUser(){
        final Gson gson = new Gson();

        String fileName = getString(R.string.sharedpreferences_file);
        SharedPreferences sharedPref = getSharedPreferences(fileName, Context.MODE_PRIVATE);
        String profile = sharedPref.getString("profile", "{}");
        Usuario user = gson.fromJson(profile, Usuario.class);
        return user;
    }

    public String cargaemail(){
        Usuario user = this.getUser();
        return user.getEmail();
    }

    public String cargaUsername(){
        Usuario user = this.getUser();
        return user.getUsername();
    }

    public String cargaDescription(){
        Usuario user = this.getUser();
        return user.getDescription();
    }


    public String idDOcUserLOgueado(){
        String fileName = getString(R.string.sharedpreferences_file);
        SharedPreferences sharedPref = getSharedPreferences(fileName, Context.MODE_PRIVATE);
        String busca = sharedPref.getString("id", "No existe");
        return busca;
    }

    public void cargaDocumentoUsuarioLogueado(String id){

        DocumentReference docRef = db.collection("users").document(id);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                Usuario u = value.toObject(Usuario.class);
                saveOnShared(u);

            }
        });


    }


    private void saveOnShared(Usuario user) {

        if (user == null){
            return;
        }

        String fileName = getString(R.string.sharedpreferences_file);
        SharedPreferences sharedPref = getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        final Gson gson = new Gson();
        String jsonData = gson.toJson(user);
        editor.putString("profile", jsonData );

        editor.commit();

    }

    public void iniciaActivity (Activity act){
        Intent intent = new Intent(this, act.getClass());
        startActivity(intent);
    }

    public void changeFragmentMain(Crea frg){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();

        trans.replace(R.id.act_main_menu_lin, frg, "fragment_01");

        trans.commit();
    }

}
