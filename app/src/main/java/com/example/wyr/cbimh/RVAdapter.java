package com.example.wyr.cbimh;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wyr on 2018/3/24.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.InformViewHolder>{

    public static class InformViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView title;
        TextView content;

        InformViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            title = (TextView)itemView.findViewById(R.id.title_inform);
            content = (TextView)itemView.findViewById(R.id.content_inform);
        }
    }

    List<Home.Inform> informs;

    RVAdapter(List<Home.Inform> persons){
        this.informs = persons;
    }

    @Override
    public int getItemCount() {
        return informs.size();
    }

    @Override
    public InformViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        InformViewHolder pvh = new InformViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(InformViewHolder InformViewHolder, int i) {
        InformViewHolder.title.setText(informs.get(i).title);
        InformViewHolder.content.setText(informs.get(i).content);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
