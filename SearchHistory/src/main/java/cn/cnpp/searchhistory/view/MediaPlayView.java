package cn.cnpp.searchhistory.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import cn.cnpp.searchhistory.R;

/**
 * 文件描述
 *
 * @auther: create by panxiang.song
 * @time: 18-11-17 下午3:29
 * @email: panxiang.song@megatronix.co
 */
public class MediaPlayView extends RelativeLayout implements View.OnClickListener {
    private LinearLayout llController;
    private ImageView ivPlayController;
    private TextView tvSongCount;

    private OnPlayControllerListener listener;

    public void setListener(OnPlayControllerListener listener) {
        this.listener = listener;
    }

    public MediaPlayView(Context context) {
        this(context, null);
    }

    public MediaPlayView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MediaPlayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.include_paly_controller, null);
        ivPlayController = view.findViewById(R.id.iv_play_controller);
        tvSongCount = view.findViewById(R.id.tv_song_count);
        llController = view.findViewById(R.id.ll_controller);
        llController.setOnClickListener(this);
        addView(view);
    }


    public void updatePlayStatus(boolean isPlay) {
        if (isPlay) {
            ivPlayController.setImageResource(R.drawable.icon_play_all);
        } else {
            ivPlayController.setImageResource(R.drawable.icon_play_pause);
        }
    }


    public void updateSongCount(int count) {
        String Str = getResources().getString(R.string.usb_tracklist_num);
        tvSongCount.setText(String.format(Str, count));

    }

    public interface OnPlayControllerListener {
        void onViewClick();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_controller:
                Log.i("xiangxiang","play all");
                if (listener != null) {
                    listener.onViewClick();
                }
                break;
        }
    }
}
