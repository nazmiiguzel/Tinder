package com.example.digitustinder;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.huxq17.swipecardsview.SwipeCardsView;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {



     SwipeCardsView swipeFlingAdapterView;
     List<ApiPhoto> modelList = new ArrayList<>();


    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private Uri ImageUri;

    private HomeFragmentAdapter homeFragmentAdapter;









    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home,container,false);

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        final SwipeCardsView swipeFlingAdapterView = v.findViewById(R.id.swipeCardsView) ;

        Call<List<ApiPhoto>> call = api.getApiPhotos();

        call.enqueue(new Callback<List<ApiPhoto>>() {
            @Override
            public void onResponse(Call<List<ApiPhoto>> call, Response<List<ApiPhoto>> response) {
                List<ApiPhoto> apiPhotos = response.body();
                homeFragmentAdapter = new HomeFragmentAdapter(apiPhotos,getContext());
                swipeFlingAdapterView.setAdapter(homeFragmentAdapter);

            }

            @Override
            public void onFailure(Call<List<ApiPhoto>> call, Throwable t) {

                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        return v;
    }
}
