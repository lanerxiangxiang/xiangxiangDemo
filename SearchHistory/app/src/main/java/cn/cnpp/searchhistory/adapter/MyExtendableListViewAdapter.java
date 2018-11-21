package cn.cnpp.searchhistory.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.cnpp.searchhistory.R;

/**
 * 文件描述
 *
 * @auther: create by panxiang.song
 * @time: 18-11-18 下午9:19
 * @email: panxiang.song@megatronix.co
 */
public class MyExtendableListViewAdapter extends BaseExpandableListAdapter {
    private static final int TYPE_TOP = 0;
    private static final int TYPE_BOTTOM = 1;
    private static final int TYPE_MIDDLE = 2;
    private Context mcontext;
    public String[] groupString = {"射手", "辅助", "坦克", "法师"};
    public String[][] childString = {
            {"孙尚香", "后羿", "马可波罗", "狄仁杰"},
            {"孙膑", "蔡文姬", "鬼谷子", "杨玉环"},
            {"张飞", "廉颇", "牛魔", "项羽"},
            {"诸葛亮", "王昭君", "安琪拉", "干将"}};


    @Override
    // 获取分组的个数
    public int getGroupCount() {
        return groupString.length;
    } //获取指定分组中的子选项的个数

    @Override
    public int getChildrenCount(int groupPosition) {
        return childString[groupPosition].length;
    }

    //        获取指定的分组数据
    @Override
    public Object getGroup(int groupPosition) {
        return groupString[groupPosition];
    } //获取指定分组中的指定子选项数据

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childString[groupPosition][childPosition];
    } //获取指定分组的ID, 这个ID必须是唯一的

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //获取子选项的ID, 这个ID必须是唯一的
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
    @Override
    public boolean hasStableIds() {
        return true;
    }


    @Override
    public int getChildType(int groupPosition, int childPosition) {
        try {
            int size = getChildrenCount(groupPosition);
            if (childPosition == 0) {
                return TYPE_TOP;
            } else if (childPosition == size - 1) {
                return TYPE_BOTTOM;
            } else {
                return TYPE_MIDDLE;
            }
        } catch (Exception e) {
// TODO: handle exception
            System.out.println("Exception-->>" + e.toString());
            return TYPE_BOTTOM;
        }

    }

    @Override
    public int getChildTypeCount() {
        return 3;
    }

    /**
     * 获取显示指定组的视图对象
     *
     * @param groupPosition 组位置
     * @param isExpanded    该组是展开状态还是伸缩状态
     * @param convertView   重用已有的视图对象
     * @param parent        返回的视图对象始终依附于的视图组
     */ // 获取显示指定分组的视图
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.partent_item, parent, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.label_group_normal);
            groupViewHolder.parentImageViw = convertView.findViewById(R.id.parentImageViw);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        if (isExpanded) {
            groupViewHolder.parentImageViw.setImageResource(R.drawable.icon_down);
        } else {
            groupViewHolder.parentImageViw.setImageResource(R.drawable.icon_up);
        }

        groupViewHolder.tvTitle.setText(groupString[groupPosition]);
        return convertView;
    }

    /**
     * 获取一个视图对象，显示指定组中的指定子元素数据。
     *
     * @param groupPosition 组位置
     * @param childPosition 子元素位置
     * @param isLastChild   子元素是否处于组中的最后一个
     * @param convertView   重用已有的视图(View)对象
     * @param parent        返回的视图(View)对象始终依附于的视图组
     * @return
     * @see android.widget.ExpandableListAdapter#
     * <p>
     * getChildView(int, int, boolean, android.view.View,
     * android.view.ViewGroup)
     */ //取得显示给定分组给定子位置的数据用的视图
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
       int type = getChildType(groupPosition,childPosition);
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item, parent, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.expand_child);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }

        switch (type){
            case TYPE_TOP:
                childViewHolder.tvTitle.setTextColor(0xffff0000);
                break;
            case TYPE_BOTTOM:
                childViewHolder.tvTitle.setTextColor(0xffffff00);
                break;
            case TYPE_MIDDLE:
                childViewHolder.tvTitle.setTextColor(0xff00ff00);
                break;
        }
        childViewHolder.tvTitle.setText(childString[groupPosition][childPosition]);
        return convertView;
    }

    //指定位置上的子元素是否可选中
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        TextView tvTitle;
        ImageView parentImageViw;
    }

    static class ChildViewHolder {
        TextView tvTitle;
    }


}
