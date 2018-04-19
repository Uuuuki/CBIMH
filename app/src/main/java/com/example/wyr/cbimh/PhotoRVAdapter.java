package com.example.wyr.cbimh;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PhotoRVAdapter extends RecyclerView.Adapter<PhotoRVAdapter.InformViewHolder>{

    public static class InformViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        TextView message;

        InformViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.photo_cv);
            name = (TextView) itemView.findViewById(R.id.parent_name);
            message = (TextView) itemView.findViewById(R.id.parent_message);
        }
    }

    List<Photo.Inform> informs;

    PhotoRVAdapter(List<Photo.Inform> persons){
        this.informs = persons;
    }

    @Override
    public int getItemCount() {
        return informs.size();
    }

    @Override
    public PhotoRVAdapter.InformViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_photo, viewGroup, false);
        PhotoRVAdapter.InformViewHolder pvh = new PhotoRVAdapter.InformViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PhotoRVAdapter.InformViewHolder InformViewHolder, int i) {
        InformViewHolder.name.setText(informs.get(i).name);
        InformViewHolder.message.setText(informs.get(i).message);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
