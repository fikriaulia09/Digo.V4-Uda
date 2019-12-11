package com.example.digo.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digo.Favorite.FavoriteAdapter;
import com.example.digo.Home.Information;
import com.example.digo.Home.InformationData;
import com.example.digo.R;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment{

    private FavoriteViewModel favoriteViewModel;


    private RecyclerView rvMenus;
    private ArrayList<com.example.digo.Favorite.Information> datahome = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
//        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
//        View root = inflater.inflate(R.layout.favorite_fragment,container,false);
//
//        ImageView angsoduo = (ImageView) root.findViewById(R.id.angsoduo);
//        angsoduo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ViewTempatActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        return root;

        View view = inflater.inflate(R.layout.favorite_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_data_favorite);

        FavoriteAdapter adapter = new FavoriteAdapter(datahome);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        datahome.addAll(com.example.digo.Favorite.InformationData.getListData());


        return view;

    }
}
