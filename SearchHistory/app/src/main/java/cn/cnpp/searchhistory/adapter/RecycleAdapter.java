package cn.cnpp.searchhistory.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.cnpp.searchhistory.R;

/**
 * 文件描述
 *
 * @auther: create by panxiang.song
 * @time: 18-11-20 下午1:24
 * @email: panxiang.song@megatronix.co
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mList;
    private LayoutInflater mLayoutInflater;


    public RecycleAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_folder, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (mList != null) {
            String content = mList.get(i);
            if (i == mList.size() - 1) {
//                    隐藏箭头
                viewHolder.iv.setVisibility(View.GONE);

            } else {
                viewHolder.iv.setVisibility(View.VISIBLE);
            }

            viewHolder.folderName.setText(content);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView folderName;
        ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            folderName = itemView.findViewById(R.id.folder_name);
            iv = itemView.findViewById(R.id.iv_arrow);
        }
    }
}
