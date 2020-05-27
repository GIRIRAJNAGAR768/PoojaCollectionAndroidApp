package com.custom.blouse.designs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;



public class GalleryGridAdapter extends RecyclerView.Adapter<GalleryGridAdapter.ViewHolder> {
    private Activity activity;
    private List<String> link;
    private List<String> key;
    private String horf;

    GalleryGridAdapter(Activity activity,List<String> links,String horf,List<String> keys) {
        this.link=links;
        this.activity=activity;
        this.horf=horf;
        this.key=keys;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final  int position) {
        Glide.with(activity).load(link.get(position)).thumbnail(Glide.with(activity).load(R.drawable.loading)).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Dev","Clicked");
                Intent in = new Intent(activity,ShowDesign.class);
                in.putExtra("Link",link.get(position));
                in.putExtra("horf",horf);
                in.putExtra("code",key.get(position));
                activity.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return link.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item);
        }

    }
}
