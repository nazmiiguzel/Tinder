package com.example.digitustinder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchFragmentAdapter extends RecyclerView.Adapter<SearchFragmentAdapter.ImageViewHolder> {

        private Context context;
        private ArrayList<UserInformation> userList;


    public SearchFragmentAdapter(Context c, ArrayList<UserInformation> p){

       this.context = c;
       this.userList = p;
    }


    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.search_item,parent,false);


        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder viewHolder, int i) {

        final String hisUid = userList.get(i).getUid();

        final UserInformation userInformation = userList.get(i);
        viewHolder.post_text.setText(userInformation.getNamee());
        viewHolder.url=Uri.parse(userInformation.getImaage_uri());

        Picasso.get().load(viewHolder.url).into(viewHolder.post_image);


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ChatActivity.class);
                intent.putExtra("hisUid" , hisUid);
                context.startActivity(intent);

            }
        });




    }

    @Override
    public int getItemCount() {
        return userList.size();
    }




    public static class ImageViewHolder extends RecyclerView.ViewHolder{

        ImageView post_image;
        TextView post_text;
        Uri url;

        public ImageViewHolder(View itemView){
            super(itemView);

            post_image = itemView.findViewById(R.id.post_image);
            post_text = itemView.findViewById(R.id.post_title);

        }
    }
}
