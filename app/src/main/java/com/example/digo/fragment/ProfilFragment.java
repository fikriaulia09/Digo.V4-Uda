package com.example.digo.fragment;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.digo.LoginActivity;
import com.example.digo.MainActivity;
import com.example.digo.R;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class ProfilFragment extends Fragment{
    private FirebaseAuth firebaseauth;
    private ProfilViewModel profilViewModel;
    public static final String SHARED_PREFS = "sharedPrefs";
    public int loginstatus;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        SharedPreferences mySharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        loginstatus = mySharedPreferences.getInt("LOGIN_STATUS", 0);
        if(loginstatus==1){
            Log.d("LoginStatus","LogedIn");
            profilViewModel = ViewModelProviders.of(this).get(ProfilViewModel.class);
            View root = inflater.inflate(R.layout.logedin_profil_fragment,container,false);

            TextView logout = (TextView) root.findViewById(R.id.keluar);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        firebaseauth.getInstance().signOut();
                        SharedPreferences mySharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = mySharedPreferences.edit();
                        editor.putInt("LOGIN_STATUS",0);
                        editor.apply();
                        Log.d("LogoutStatus","Berhasil Logout");

                }
            });

            return root;
        }
        else{
            Log.d("LoginStatus","TidakLogin");
            profilViewModel = ViewModelProviders.of(this).get(ProfilViewModel.class);
            View root = inflater.inflate(R.layout.profil_fragment,container,false);

            Button login = (Button)root.findViewById(R.id.login_btn);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
            return root;
        }

    }

}
