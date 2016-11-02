package com.wbh.day35_recycleviewdifftype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.wbh.day35_recycleviewdifftype.adapter.MyAdapter;
import com.wbh.day35_recycleviewdifftype.bean.Info;

import java.util.ArrayList;
import java.util.List;

/**
 * 加载不同布局,模拟聊天页面,2种类型
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView recycleView;
    private List<Info> list = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        //配置布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(manager);

        for (int i = 0; i < 100; i++) {
            Info info = new Info();
            if (i % 2 == 0) {
                info.setType(0);
            } else {
                info.setType(1);
            }
            info.setMessage("======" + i + "======");
            list.add(info);
        }
        adapter = new MyAdapter(list, this);
        recycleView.setAdapter(adapter);
        View headView = LayoutInflater.from(this).inflate(R.layout.headitem_layout, null);
        adapter.addHeadView(headView);
    }
}
