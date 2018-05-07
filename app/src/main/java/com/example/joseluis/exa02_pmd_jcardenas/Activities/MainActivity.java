package com.example.joseluis.exa02_pmd_jcardenas.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joseluis.exa02_pmd_jcardenas.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
   private  BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.producto:

                    break;
                case R.id.lista:
                    Intent intent3 =new Intent(MainActivity.this,Fragment_lista.class);
                    startActivity(intent3);
                    break;
                case R.id.venta:
                    Intent intent2 =new Intent(MainActivity.this,Fragment_venta.class);
                    startActivity(intent2);
                   break;
            }
            return false;
        }
    };
    EditText id,name,price;
    Button add;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_producto);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        id=(EditText)findViewById(R.id.ETid);
        name=(EditText)findViewById(R.id.ETnombre);
        price=(EditText)findViewById(R.id.ETprecio);
        add=(Button)findViewById(R.id.guardar);

        databaseReference = FirebaseDatabase.getInstance().getReference().getRoot();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarproducto();
                id.setFocusable(true);
            }
        });
    }

    private void registrarproducto() {
        //        int identificador = Integer.parseInt(idpro.getText().toString().trim());
        String identificador = id.getText().toString().trim();
        String nombreproducto = name.getText().toString().trim();
        double precioproducto = Double.parseDouble(price.getText().toString().trim());

//        if (String.valueOf(identificador)==""){
        if (TextUtils.isEmpty(identificador)){
            Toast.makeText(this, "Por favor ingresa un ID", Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(nombreproducto)){
            Toast.makeText(this, "Por favor ingresa un producto", Toast.LENGTH_SHORT).show();
            return;
        }else if (String.valueOf(precioproducto)==""){
            Toast.makeText(this, "Por favor ingresa un precio", Toast.LENGTH_SHORT).show();
            return;

        }else{
            String id = databaseReference.push().getKey();
//            Producto producto = new Producto(id,nombre,precio,stock);
//            databaseReference.child(id).child("id").setValue(identificador);
            databaseReference.child(id).child("id").setValue(identificador.toString().trim());
            databaseReference.child(id).child("nombre").setValue(nombreproducto.toString().trim());
            databaseReference.child(id).child("precio").setValue(precioproducto);
            Toast.makeText(this, "Producto Registrado Satisfactoriamente", Toast.LENGTH_SHORT).show();
        }


        limpiarcampos();

    }

    private void limpiarcampos() {
        id.setText("");
        name.setText("");
        price.setText("");
    }
}
