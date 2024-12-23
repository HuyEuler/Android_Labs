package com.example.learn2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {
    private final List<File> files;
    private final OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(File file);
    }

    public FileAdapter(List<File> files, OnItemClickListener onItemClickListener) {
        this.files = files;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file, parent, false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        holder.bind(files.get(position));
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public class FileViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

        public void bind(File file) {
            textView.setText(file.getName());
            itemView.setOnClickListener(v -> onItemClickListener.onItemClick(file));
        }
    }
}

