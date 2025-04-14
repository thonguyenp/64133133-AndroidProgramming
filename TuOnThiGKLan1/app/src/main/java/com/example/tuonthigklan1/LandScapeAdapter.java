package com.example.tuonthigklan1;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LandScapeAdapter extends RecyclerView.Adapter<LandScapeAdapter.ItemHolder> {
    Context context;

    ArrayList<LandScape> dsDuLieu;

    public LandScapeAdapter(Context context, ArrayList<LandScape> dsDuLieu) {
        this.context = context;
        this.dsDuLieu = dsDuLieu;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vItem = inflater.inflate(R.layout.item_land,parent,false);
        ItemHolder holder = new ItemHolder(vItem);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        LandScape selectedls = dsDuLieu.get(position);
        String ten = selectedls.getLandCaption();
        holder.txtCaption.setText(ten);

        String tenAnh = selectedls.getImgFile();
        String packageName = context.getPackageName();
        int imgId = context.getResources().getIdentifier(tenAnh,"mipmap",packageName);
        holder.img.setImageResource(imgId);
    }

    @Override
    public int getItemCount() {
        return dsDuLieu.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder
    {
        TextView txtCaption;
        ImageView img;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            this.txtCaption = itemView.findViewById(R.id.txtCaption);
            this.img = itemView.findViewById(R.id.imgFileName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LandScape selectedls = dsDuLieu.get(getAdapterPosition());
                    String ten = selectedls.getLandCaption();
                    String tenImg = selectedls.getImgFile();
                    Intent ibai2sub = new Intent(context,Bai2SubActivity.class);
                    ibai2sub.putExtra("ten",ten);
                    String packageName = context.getPackageName();
                    int imgId = context.getResources().getIdentifier(tenImg,"mipmap",packageName);
                    ibai2sub.putExtra("imgId",imgId);
                    context.startActivity(ibai2sub);
                }
            });
        }
    }
}
