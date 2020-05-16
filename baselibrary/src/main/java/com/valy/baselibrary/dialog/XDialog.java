package com.valy.baselibrary.dialog;

import android.content.Context;

import org.jetbrains.annotations.NotNull;

/**
 * @Auther: lzy
 * @datetime: 2020/3/19
 * @desc:
 */
public class XDialog {
    // TODO: 2020/3/19 全局弹窗的配置
    private String mainColor;

    public String getMainColor() {
        return mainColor;
    }

    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }

    public XDialog() {

    }

    public static class Builder {
        private final PopupWindowInfo popupWindowInfo = new PopupWindowInfo();
        private Context mContext;

        public Builder(@NotNull Context activity) {
            mContext = activity;
        }

        public Builder setContentView(int reslayout) {
            this.popupWindowInfo.resId = reslayout;
            return this;
        }

        public Builder setIsDarkWindow(boolean isDarkWindow) {
            this.popupWindowInfo.isDarkWindow = isDarkWindow;
            return this;
        }

        public Builder setOutsideTouchable(boolean ok) {
            this.popupWindowInfo.isOutsideTouchable = ok;
            return this;
        }

        public Builder setFocusable(boolean focusable) {
            this.popupWindowInfo.isFocusable = focusable;
            return this;
        }

        public Builder setWidth(int mWidth) {
            this.popupWindowInfo.mWidth = mWidth;
            return this;
        }

        public Builder setHeight(int mHeight) {
            this.popupWindowInfo.mHeight = mHeight;
            return this;
        }

        /**
         * public CenterListPopupView asCenterList(String title, String[] data, int[] iconIds, int checkedPosition, OnSelectListener selectListener) {
         * popupType(PopupType.Center);
         * CenterListPopupView popupView = new CenterListPopupView(this.context)
         * .setStringData(title, data, iconIds)
         * .setCheckedPosition(checkedPosition)
         * .setOnSelectListener(selectListener);
         * popupView.popupInfo = this.popupInfo;
         * return popupView;
         * }
         * TODO: 2020/3/19 这里写一个类去实现
         */


        public BasePopupWindows asCustom(BasePopupWindows basePopupWindows) {
            basePopupWindows.popupWindowInfo = popupWindowInfo;
            return basePopupWindows;
        }
    }
}
