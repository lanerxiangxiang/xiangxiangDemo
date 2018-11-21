package cn.cnpp.searchhistory.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.cnpp.searchhistory.R;
import cn.cnpp.searchhistory.adapter.RecycleAdapter;
import cn.cnpp.searchhistory.adapter.RecyclerItemClickListener;
import cn.cnpp.searchhistory.utils.ListUtils;
import cn.cnpp.searchhistory.utils.NewListUtils;

/**
 * 文件描述
 *
 * @auther: create by panxiang.song
 * @time: 18-11-20 下午1:22
 * @email: panxiang.song@megatronix.co
 */
public class RecycleActivity extends Activity {
    @BindView(R.id.rvTitle)
    RecyclerView rvTitle;
    @BindView(R.id.tv_root)
    TextView tvRoot;
    @BindView(R.id.tv_middle)
    TextView tvMiddle;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.tv_last)
    TextView tvLast;
    private LinearLayoutManager manager;
    private RecycleAdapter adapter;
    private List<String> mlist = new ArrayList<>();
    private List<String> titleList;

    private RecyclerItemClickListener itemClickListener;

    public static String[] searchWord = {"净水器手机电动车洗衣机沙发", "冰箱热水器床集成灶集成灶领带"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        ButterKnife.bind(this);


        itemClickListener = new RecyclerItemClickListener(RecycleActivity.this, rvTitle, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });


        rvTitle.setNestedScrollingEnabled(false);
        rvTitle.addOnItemTouchListener(itemClickListener);
        manager = new LinearLayoutManager(RecycleActivity.this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mlist = new ArrayList(Arrays.asList(searchWord));
        titleList = NewListUtils.getTitleList(mlist, RecycleActivity.this);

        adapter = new RecycleAdapter(RecycleActivity.this, titleList);

        rvTitle.setLayoutManager(manager);
        rvTitle.setAdapter(adapter);

//        initView();


    }


    public void initView() {
        tvLast.setVisibility(View.VISIBLE);
        tvThree.setVisibility(View.VISIBLE);
        tvRoot.setVisibility(View.VISIBLE);
        tvMiddle.setVisibility(View.VISIBLE);
        if (titleList.size() <= 3) {
            tvMiddle.setVisibility(View.INVISIBLE);
            if (titleList.size() <= 2) {
                tvThree.setVisibility(View.INVISIBLE);
                if (titleList.size() == 1) {
                    tvLast.setVisibility(View.INVISIBLE);
                } else {
                    tvLast.setText(titleList.get(titleList.size() - 1));
                }
            } else {
                tvThree.setText(titleList.get(titleList.size() - 2));
                tvLast.setText(titleList.get(titleList.size() - 1));
            }

        } else {
            tvMiddle.setVisibility(View.VISIBLE);
            tvThree.setText(titleList.get(titleList.size() - 2));
            tvLast.setText(titleList.get(titleList.size() - 1));
        }


        tvRoot.setText(titleList.get(0));


    }


    @OnClick({R.id.tv_root, R.id.tv_three, R.id.tv_last})

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_root:
                mlist.clear();
                mlist.add(searchWord[0]);
                titleList = ListUtils.getTitleList(mlist);
                initView();
                break;
            case R.id.tv_three:
                mlist.remove(mlist.size() - 1);
                titleList = ListUtils.getTitleList(mlist);
                initView();
                break;
            case R.id.tv_last:
                break;
        }
    }
}
