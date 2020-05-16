package com.valy.baselibrary.adpter.sticky;

/**
 * @Auther: valy
 * @datetime: 2020/3/31
 * @desc:
 */
public interface GroupListener {

    /**
     * 获取组名，用于判断是否是同一组
     *
     * @param position
     * @return
     */
    String getGroupName(int position);
}
