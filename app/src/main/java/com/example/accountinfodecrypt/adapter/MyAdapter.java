package com.example.accountinfodecrypt.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.accountinfodecrypt.R;
import com.example.accountinfodecrypt.model.AccountInfo;

import java.util.List;

/**
 * Created by Nguyen Tuan Anh on 21/07/2022.
 * FPT Software
 * tuananhprogrammer@gmail.com
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<AccountInfo> accountInfoList;
    private OnItemClickListener onItemClickListener;

    public MyAdapter(OnItemClickListener onItemClickListener, List<AccountInfo> accountInfoList) {
        this.accountInfoList = accountInfoList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_info, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AccountInfo accountInfo = accountInfoList.get(position);
        holder.textWebsite.setText(accountInfo.getWebsite());
        holder.textUsername.setText(accountInfo.getUsername());
    }

    @Override
    public int getItemCount() {
        return accountInfoList.size();
    }

    public void setAccountInfoList(List<AccountInfo> accountInfoList) {
        this.accountInfoList = accountInfoList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textWebsite, textUsername;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textWebsite = itemView.findViewById(R.id.text_website);
            textUsername = itemView.findViewById(R.id.text_username);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition(), v, false);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view, boolean isLongClick);
    }
}
