package com.example.digo.Favorite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.digo.R;

import java.util.ArrayList;


public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>{

    private ArrayList<Information> listdatahome;

    public FavoriteAdapter(ArrayList<Information> list) {
        this.listdatahome = list;
    }



    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_favorite, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

        Information listdata = listdatahome.get(position);
        Glide.with(holder.itemView.getContext())
                .load(listdata.getImage())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imgPhoto);
        holder.tvNama.setText(listdata.getNama());
        holder.tvLokasi.setText(listdata.getLokasi());

    }

    @Override
    public int getItemCount() {
        return listdatahome.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvNama, tvLokasi, tvRating;
        ImageView imgPhoto;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_item_nama_fv);
            imgPhoto = itemView.findViewById(R.id.img_item_photo_fv);
            tvLokasi = itemView.findViewById(R.id.tv_item_lokasi_fv);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
