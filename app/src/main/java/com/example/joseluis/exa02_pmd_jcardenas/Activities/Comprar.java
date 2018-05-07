package com.example.joseluis.exa02_pmd_jcardenas.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.joseluis.exa02_pmd_jcardenas.R;

public class Comprar extends AppCompatActivity {
    private TextView npro,total;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar);

        String producto= "";
        String precio="";
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            producto = extras.getString("producto");
            precio = extras.getString("precio");
        }

        npro = (TextView) findViewById(R.id.textnamepro);
        npro.setText(producto);

        total = (TextView) findViewById(R.id.totalpro);
        total.setText("S/. "+precio);
    }
}
