package com.harsh.authentication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLData;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    int single;
    ArrayList<Model> modelArrayList;
    SQLiteDatabase sqLiteDatabase;
    private OnNoteListner onNoteListner;

    public MyAdapter(Context context, int single, ArrayList<Model> modelArrayList, SQLiteDatabase sqLiteDatabase,OnNoteListner onNoteListner) {
        this.context = context;
        this.single = single;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
        this.onNoteListner = onNoteListner;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singledata,null);
        return new ViewHolder(view,onNoteListner);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        final Model model = modelArrayList.get(position);
        byte[] image = model.getAvatar();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        holder.imageavatar.setImageBitmap(bitmap);
        holder.txtname.setText(model.getTitle());
        holder.txtdesc.setText(model.getDescription());
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageavatar;
        TextView txtname,txtdesc;
        OnNoteListner onNoteListner;
        public ViewHolder(@NonNull View itemView,OnNoteListner onNoteListner) {
            super(itemView);
            imageavatar = (ImageView) itemView.findViewById(R.id.viewavatar);
            txtname = (TextView) itemView.findViewById(R.id.txt_name);
            txtdesc = (TextView) itemView.findViewById(R.id.txt_des);
            this.onNoteListner = onNoteListner;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListner.onNoteClick(getAdapterPosition());
        }
    }
    public interface OnNoteListner{
        void onNoteClick(int position);
    }
}
