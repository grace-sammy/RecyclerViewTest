package com.example.sammy.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<MainData> list = new ArrayList<>();
    RecyclerView recyclerView;
    Button btnAdd;

    MainAdapter mainAdapter;
    //리스트 뷰와 다른 점
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        btnAdd=findViewById(R.id.btnAdd);

        //
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //
        mainAdapter=new MainAdapter(R.layout.list_item, list);
        recyclerView.setAdapter(mainAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainData mainData=new MainData(R.mipmap.ic_launcher, "홍길동", "새로운나라");
                list.add(mainData);
                //어댑터에게 통보
                mainAdapter.notifyDataSetChanged();
            }
        });
    }
}
