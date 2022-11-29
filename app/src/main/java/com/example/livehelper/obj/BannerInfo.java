package com.zrkaxt.testmachine.obj;


import com.stx.xhb.androidx.entity.BaseBannerInfo;

public class BannerInfo implements BaseBannerInfo {

    String url;

    public BannerInfo(String url) {
        this.url = url;
    }

    @Override
    public Object getXBannerUrl() {
        return url;
    }

    @Override
    public String getXBannerTitle() {
        return null;
    }
}
