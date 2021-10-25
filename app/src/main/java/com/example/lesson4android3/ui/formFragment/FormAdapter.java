package com.example.lesson4android3.ui.formFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson4android3.R;
import com.example.lesson4android3.data.models.PostModel;
import com.example.lesson4android3.databinding.ListItemBinding;
import com.example.lesson4android3.remote.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.ViewHolder> {

    private List<PostModel> list = new ArrayList<>();
    private OnItemCLickListener onItemCLickListener;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    public void setOnItemCLickListener(OnItemCLickListener onItemCLickListener) {
        this.onItemCLickListener = onItemCLickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(ArrayList<PostModel> body) {
        list.addAll(body);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ListItemBinding binding;
        public ViewHolder(@NonNull ListItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(PostModel model) {
            binding.textTv.setText(model.getTitle());
            itemView.setOnClickListener(view -> {
                onItemCLickListener.clickItem(model);
            });

            itemView.setOnLongClickListener(view -> {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext())
                        .setMessage("You serious?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                RetrofitClient.getApi().deleteMockerModel(model.getId()).enqueue(new Callback<PostModel>() {
                                    @Override
                                    public void onResponse(Call<PostModel> call, Response<PostModel> response) {

                                        list.remove(getAdapterPosition());
                                        notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onFailure(Call<PostModel> call, Throwable t) {

                                    }
                                });
                            }
                        })
                        .setNegativeButton("no",null);
                alert.create().show();
                return true;
            });

        }
    }
    public interface OnItemCLickListener{
        void clickItem(PostModel model);
    }

}

