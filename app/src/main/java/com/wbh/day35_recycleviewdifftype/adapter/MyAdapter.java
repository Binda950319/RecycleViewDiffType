package com.wbh.day35_recycleviewdifftype.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wbh.day35_recycleviewdifftype.R;
import com.wbh.day35_recycleviewdifftype.bean.Info;

import java.util.List;

/**
 * Created by Administrator on 2016/10/12.
 * <p/>
 * 缓存:
 * 强引用:内存
 * 弱引用:WeakReference
 * 软引用:SoftReference
 * 软引用是对强引用的一个补充,Lru移除的数据可能还会存储到软引用
 */

/**
 * 加载不同布局,只需要额外添加一个方法:getItemViewType(int position)根据指定位置得到View的类型;
 * 1.执行顺序:getItemCount():先得到集合的数量;
 * 2.getItemViewType(int position)根据指定位置得到View类型,
 *    然后执行 onCreateViewHolder(ViewGroup parent, int viewType):执行一屏幕item的创建,
 *    超过一屏幕复用;
 * 3.找控件, ViewHolder的创建需要更改默认的构造方法,添加type, 更具type决定去查询那个控件;
 * 4.填充数据:onBindViewHolder():调用getItemViewType(pos)得到类型去匹配设置数据
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Info> list;
    private Context context;
    private LayoutInflater inflater;
    private static final int TYPE_LEFT = 0;
    private static final int TYPE_RIGHT = 1;
    private static final int TYPE_HEAD = 2;
    private View headView = null;

    public MyAdapter(List<Info> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addHeadView(View view){
        this.headView = view;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case TYPE_HEAD:
                view = headView;
                break;
            case TYPE_LEFT:
                view = inflater.inflate(R.layout.leftitem_layout, parent, false);
                break;
            case TYPE_RIGHT:
                view = inflater.inflate(R.layout.rightitem_layout, parent, false);
                break;
        }
        return new MyViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        int type = getItemViewType(position);
        switch (type) {
            case TYPE_HEAD:

                break;
            case TYPE_LEFT:
                String message = list.get(position-1).getMessage();
                holder.left_textView.setText(message);
                break;
            case TYPE_RIGHT:
                String message1 = list.get(position-1).getMessage();
                holder.right_textView.setText(message1);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    //根据指定位置得到View的类型
    @Override
    public int getItemViewType(int position) {
        int type = 0;
        if (position == 0){
            type = TYPE_HEAD;
        } else{
            Info info = list.get(position - 1);
            type = info.getType();
        }
        return type;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private int type;
        private TextView left_textView, right_textView;

        public MyViewHolder(View itemView, int type) {
            super(itemView);
            this.type = type;
            switch (type) {
                case TYPE_HEAD:
                    break;
                case TYPE_LEFT:
                    left_textView = (TextView) itemView.findViewById(R.id.left_textView);
                    break;
                case TYPE_RIGHT:
                    right_textView = (TextView) itemView.findViewById(R.id.right_textView);
                    break;
            }
        }
    }
}
