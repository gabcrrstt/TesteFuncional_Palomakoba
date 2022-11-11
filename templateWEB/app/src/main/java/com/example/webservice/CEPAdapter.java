package com.example.webservice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CEPAdapter extends RecyclerView.Adapter<CEPAdapter.MyViewHolder>
    {
    private List<DBCep>  cepList = new ArrayList<DBCep>();


    public CEPAdapter(List<DBCep> lista)
        {
        this.cepList = lista;
        }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
        {
        StringBuilder mod = new StringBuilder();

        mod.append(this.cepList.get(position).logradouro)
                .append("\n")
                .append(this.cepList.get(position).bairro)
                .append("\n")
                .append(this.cepList.get(position).localidade);

        holder.cep.setText(this.cepList.get(position).cep);

        holder.endereco.setText(mod.toString());
        }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cep_adapter_view, parent, false);
            return new MyViewHolder(itemLista);
        }


    @Override
    public int getItemCount()
        {
        return this.cepList.size();
        }

    public class MyViewHolder extends RecyclerView.ViewHolder
        {
        TextView cep, endereco, id;

        public MyViewHolder(View itemView)
            {
            super(itemView);
            cep = itemView.findViewById(R.id.txtCepLB);
            endereco = itemView.findViewById(R.id.txtEndLB);
            id      = itemView.findViewById(R.id.txtid);
            }
        }
    }