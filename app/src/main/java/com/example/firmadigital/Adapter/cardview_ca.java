package com.example.firmadigital.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firmadigital.R;
import com.example.firmadigital.Class.signatures;

import java.util.List;

public class cardview_ca extends RecyclerView.Adapter<cardview_ca.signature_vh> {
    private Context context;
    private List<signatures> list_s;

    public cardview_ca(Context context, List<signatures> list_s){
        this.context = context;
        this.list_s = list_s;
    }

    public signature_vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.cardview, parent, false);
        return new signature_vh(view);
    }

    public void onBindViewHolder(@NonNull signature_vh holder, int position) {
        signatures item = list_s.get(position);
        holder.tvDescripcion.setText(item.getDescripcion());

        byte[] byte_sign = item.getFirmaDigital();
        if (byte_sign != null) {
            Bitmap bit_sign = BitmapFactory.decodeByteArray(byte_sign, 0, byte_sign.length);
            holder.imgFirma.setImageBitmap(bit_sign);
        }
    }

    public int getItemCount() {
        return list_s.size();
    }

    public static class signature_vh extends RecyclerView.ViewHolder {
        ImageView imgFirma;
        TextView tvDescripcion;

        public signature_vh(View itemView) {
            super(itemView);
            imgFirma = itemView.findViewById(R.id.imgFirma);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        }
    }
}
