package com.example.joseluis.exa02_pmd_jcardenas.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;

import com.example.joseluis.exa02_pmd_jcardenas.Core.model.Producto;
import com.example.joseluis.exa02_pmd_jcardenas.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Fragment_lista extends AppCompatActivity {
    private  BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.producto:
                    Intent intent1 =new Intent(Fragment_lista.this,MainActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.lista:

                    break;
                case R.id.venta:
                    Intent intent3 =new Intent(Fragment_lista.this,Fragment_venta.class);
                    startActivity(intent3);
                    break;
            }
            return false;
        }
    };
    private Button comprarpro;
    private RecyclerView rv;
    List<Producto> productos;
    Adapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lista);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        rv = (RecyclerView) findViewById(R.id.recycler);

        rv.setLayoutManager(new LinearLayoutManager(this));
        comprarpro = (Button) findViewById(R.id.dobuy);


        productos = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        adapter = new Adapter(productos);

        rv.setAdapter(adapter);

        database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productos.removeAll(productos);
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()) {
                    Producto producto=snapshot.getValue(Producto.class);
                    productos.add(producto);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
