package com.example.sportnews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.SportViewHolder> {

    ArrayList<Sport> mSport;
    Context context;

    public SportAdapter(ArrayList<Sport> currentSport, Context context) {
        mSport = currentSport;
        this.context = context;
    }


    @NonNull
    @Override
    public SportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

          LayoutInflater inflater = LayoutInflater.from(parent.getContext());
          View view = inflater.inflate(R.layout.item_list, parent, false);
        return new SportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SportViewHolder holder, int position) {
        Sport newSport =  mSport.get(position);
        holder.bindTo(newSport);

    }

    @Override
    public int getItemCount() {
        return mSport.size();
    }

    public class SportViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title ;
        TextView subTitle;
        TextView info;
        ImageView sportImages;

        public SportViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_title);
            subTitle = itemView.findViewById(R.id.textView_info);
            sportImages = itemView.findViewById(R.id.sport_image);
            info = itemView.findViewById(R.id.textView_info_new);
            itemView.setOnClickListener(this);
        }

        public void bindTo(Sport currentSport){
            title.setText(currentSport.getTitle());
            subTitle.setText(currentSport.getSubtitle());
            info.setText(currentSport.getInfo());
            Glide.with(context).load(currentSport.getImageResource()).into(sportImages);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Sport sports = mSport.get(position);

            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", sports.getTitle());
            intent.putExtra("subtitle", sports.getSubtitle());
            intent.putExtra("info", sports.getInfo());
            intent.putExtra("sport_images", sports.getImageResource());
            context.startActivity(intent);

        }
    }
}
