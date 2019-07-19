package com.example.digitustinder;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huxq17.swipecardsview.BaseCardAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class HomeFragmentAdapter extends BaseCardAdapter {

    private             ViewHolder viewHolder ;


    private List<ApiPhoto> modelList;
    private Context context;

    public HomeFragmentAdapter( List<ApiPhoto> modelList, Context context) {
        Log.d("homeadapter","adapterbrı");
        this.modelList = modelList;
        this.context = context;
    }


    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public int getCardLayoutId() {
        return R.layout.card_item;
    }

    @Override
    public void onBindData(int position, View cardview) {
        ApiPhoto apiPhoto = modelList.get(position);

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.txtView = cardview.findViewById(R.id.image_text_view);
        viewHolder.txtView.setText(apiPhoto.getName());
        viewHolder.tinderpick = cardview.findViewById(R.id.imageView);
        viewHolder.url = Uri.parse(apiPhoto.getImageurl());
        Picasso.get().load(viewHolder.url).into(viewHolder.tinderpick);

    }





























        /*
        ViewHolder viewHolder = null;

        if (view==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.card_item,null,true);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);



        }
        else{
            viewHolder = (ViewHolder) view.getTag();

        }
                // image i ve texti api den çek


            ApiPhoto apiPhoto = (ApiPhoto) getItem(position);
        viewHolder.tinderpick =view.findViewById(R.id.imageView);
        viewHolder.name = view.findViewById(R.id.image_text_view);
            viewHolder.name.setText(apiPhoto.getName());
            viewHolder.tinderpick.setImageResource(Integer.parseInt(apiPhoto.getImageurl()));
            //view= (View) View.inflate(context,R.layout.card_item,false);

          /*  viewHolder = new ViewHolder();
            viewHolder.tinderpick =view.findViewById(R.id.imageView);
            viewHolder.name = view.findViewById(R.id.image_text_view);
            view.setTag(viewHolder);*/




           // viewHolder = (ViewHolder) view.getTag();




        /*
        TextView title = (TextView) view.findViewById(R.id.image_text_view);

        title.setText(apiPhoto.getName());
*/
        /*
            viewHolder = (ViewHolder) view.getTag();




        if (apiPhoto!=null){
            Picasso.with(context).load(apiPhoto.getImageurl()).into(viewHolder.tinderpick);
            viewHolder.name.setText(apiPhoto.getName());
        }



        //view = LayoutInflater.from(context).inflate(R.layout.card_item,parent,false);



*/




    public class ViewHolder{

        TextView txtView;

            ImageView tinderpick;
            Uri url;





    }


}
