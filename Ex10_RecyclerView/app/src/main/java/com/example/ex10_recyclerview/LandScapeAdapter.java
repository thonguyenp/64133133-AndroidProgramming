package com.example.ex10_recyclerview;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Truyền vào adapter là 1 ViewHolder
/*Thứ tự code:
* 1. Tạo class ItemLandHolder extends RecyclerView.ViewHolder
* 2. Tạo Context context và ArrayList<LandScape> listData
* 3. Tạo Constructor
* 4. Tạo Inflater trong hàm onCreateViewHolder
* 5. Truyền ViewHolder
* */
public class LandScapeAdapter extends RecyclerView.Adapter<LandScapeAdapter.ItemLandHolder> {
    Context context;
    ArrayList<LandScape> listData;

    public LandScapeAdapter(Context context, ArrayList<LandScape> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public ItemLandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vItem = inflater.inflate(R.layout.item_land,parent,false);
        ItemLandHolder holderCreated = new ItemLandHolder(vItem);
        return holderCreated;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLandHolder holder, int position) {
        //Lấy ra đối tượng hiển thị
        LandScape item = listData.get(position);
        // Trích thông tin
        String caption = item.getLandCaption();
        String tenAnh = item.getLandImgFileName();
        //Đặt vào các trường thông tin của Holder
        holder.txtCaption.setText(caption);
        //Đặt ảnh
        String packageName = holder.itemView.getContext().getPackageName();
        int imgId = holder.itemView.getResources().getIdentifier(tenAnh,"mipmap",packageName);
        holder.imgLandFileName.setImageResource(imgId);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ItemLandHolder extends RecyclerView.ViewHolder
    {
        TextView txtCaption;
        ImageView imgLandFileName;

        public ItemLandHolder(@NonNull View itemView) {
            super(itemView);
            txtCaption = itemView.findViewById(R.id.txtCaption);
            imgLandFileName = itemView.findViewById(R.id.imgViewLand);
        }
    }
}
