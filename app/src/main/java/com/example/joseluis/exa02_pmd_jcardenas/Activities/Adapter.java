package com.example.joseluis.exa02_pmd_jcardenas.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.joseluis.exa02_pmd_jcardenas.Core.model.Producto;
import com.example.joseluis.exa02_pmd_jcardenas.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ProductosviewHolder>  {
    List<Producto> productos;

    public Adapter(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public ProductosviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vistalista, parent, false);
        ProductosviewHolder holder = new ProductosviewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductosviewHolder holder, int position) {
        Producto producto = productos.get(position);
//        holder.idpro.setText(Integer.toString(producto.getId()));
        holder.idpro.setText(producto.getId());
        holder.productoname.setText(producto.getNombre());
        holder.precio.setText(Double.toString(producto.getPrecio()));

        holder.setOnclickListeners();
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }


    public static class ProductosviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Context context;
        TextView idpro, productoname, precio, stock;
        Button comprarpro;

        public ProductosviewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            idpro = (TextView) itemView.findViewById(R.id.ETid);
            productoname = (TextView) itemView.findViewById(R.id.txtproducto);
            precio = (TextView) itemView.findViewById(R.id.txtprecio);
            comprarpro = (Button) itemView.findViewById(R.id.dobuy);

        }

        void setOnclickListeners() {
            comprarpro.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dobuy:
                    Intent intent = new Intent(context, Comprar.class);
                    intent.putExtra("producto",productoname.getText().toString());
                    intent.putExtra("precio",precio.getText().toString());
                    context.startActivity(intent);
                    break;

            }
        }
    }
}
