package com.example.lt9_lvnangcao;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<Phone> {
    Activity context;
    int idLayout;
    ArrayList<Phone> list;

    //Tạo constructor để MainActivity gọi đến và truyền các tham số này vào
    //Chọn constructor mẫu đầu tiên, sau đó chọn cả 3 tham số
    //Sau đó xóa 2 tham số đầu để trở về đúng dạng: 1. Activity, 2. Layout, 3. List Data
    public MyArrayAdapter(Activity context, int idLayout, ArrayList<Phone> list) {
        super(context, idLayout, list);
        this.context = context;
        this.idLayout = idLayout;
        this.list = list;
    }

    //Gọi getView() để sắp xếp dữ liệu

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Tạo 1 đối tượng chứa Layout có kiểu là LayoutInflater
        LayoutInflater inflater = context.getLayoutInflater();
        //Đặt layout lên biến convertView được truyền vào
        convertView = inflater.inflate(idLayout,null);
        //Lấy 1 phần tử trong mảng
        Phone myPhone = list.get(position);
        //Khai báo, tham chiếu id các phần tử và hiển thị lên imageview
        ImageView imgPhone = convertView.findViewById(R.id.imgPhone);
        imgPhone.setImageResource(myPhone.getImg());
        TextView txtName = convertView.findViewById(R.id.txtName);
        txtName.setText(myPhone.getName());
        TextView txtPrice = convertView.findViewById(R.id.txtPrice);
        txtPrice.setText(myPhone.getGiaTien());
        TextView txtQuantity = convertView.findViewById(R.id.txtQuantity);
        txtQuantity.setText(myPhone.getSoLuong());
        return convertView;
    }
}
