package com.example.tuonthigklan1;

import android.content.Context;
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
    ArrayList<LandScape> listData;

    public LandScapeAdapter(Context context, ArrayList<LandScape> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vItem = inflater.inflate(R.layout.item_land,parent,false);
        ItemHolder holder = new ItemHolder(vItem);
        return holder;
    }

    //lay ra doi tuong de hien thi
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        LandScape item = listData.get(position);

        String itemCaption = item.getLandCaption();
        String tenAnh = item.getImgFile();

        holder.caption.setText(itemCaption);
        //Dat anh
        String packageName = holder.itemView.getContext().getPackageName();
        int imgId = holder.itemView.getResources().getIdentifier(tenAnh,"mipmap",packageName);
        holder.imgFile.setImageResource(imgId);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    //extends ViewHolder
    //implement View.OnClickListener
    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView caption;
        ImageView imgFile;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            this.caption = itemView.findViewById(R.id.txtCaption);
            this.imgFile = itemView.findViewById(R.id.imgFileName);
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            LandScape selectedLandScape = listData.get(getAdapterPosition());
            String ten = selectedLandScape.getLandCaption();
            String tenFile = selectedLandScape.getImgFile();
            Toast.makeText(context, "Ban vua chon: " + ten, Toast.LENGTH_SHORT).show();
        }
    }
}
