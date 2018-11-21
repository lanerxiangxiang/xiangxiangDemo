package cn.cnpp.searchhistory.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述
 *
 * @auther: create by panxiang.song
 * @time: 18-11-20 下午1:31
 * @email: panxiang.song@megatronix.co
 */
public class ListUtils {
    public static List<String> getTitleList(List<String> titles) {
        List<String> mList = new ArrayList<>();

        if (titles != null) {
            int size = titles.size();
            if (size > 3) {
                mList.add(0, getNewContent(titles.get(0)));
                mList.add(1, "...");
                mList.add(2, getNewContent(titles.get(size - 2)));
                mList.add(3, titles.get(size - 1));
            } else {
                mList = titles;
            }

        }
        return mList;
    }


    public static String getNewContent(String content) {
        String str1 = content.substring(0, 1);
        return str1 + "...";

    }
}
