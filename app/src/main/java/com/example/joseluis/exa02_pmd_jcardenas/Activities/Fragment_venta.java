package com.example.joseluis.exa02_pmd_jcardenas.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.joseluis.exa02_pmd_jcardenas.R;

public class Fragment_venta extends AppCompatActivity {
  private  BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.producto:
                    Intent intent1 =new Intent(Fragment_venta.this,MainActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.lista:
                    Intent intent3 =new Intent(Fragment_venta.this,Fragment_lista.class);
                    startActivity(intent3);
                    break;
                case R.id.venta:
                    break;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_venta);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);




    }
}
