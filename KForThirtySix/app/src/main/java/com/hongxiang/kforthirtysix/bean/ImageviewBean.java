package com.hongxiang.kforthirtysix.bean;

import java.util.List;

/**
 * Created by dllo on 16/5/31.
 */
public class ImageviewBean {


    /**
     * code : 0
     * data : {"pics":[{"action":"web","imgUrl":"https://krplus-pic.b0.upaiyun.com/201605/31/551e96e9736f9252d125c1a027553e52.jpg","location":"http://e.vhall.com/538384425","title":"体育直播"},{"action":"web","imgUrl":"https://krplus-pic.b0.upaiyun.com/201605/30/80576c88bec89a68a08a2194b66cbc30.jpg","location":"https://huodong.36kr.com/h5/star/index.html?ktm_source=xsdappbanner","title":"创业星生代排行"}]}
     * msg : 操作成功！
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * action : web
         * imgUrl : https://krplus-pic.b0.upaiyun.com/201605/31/551e96e9736f9252d125c1a027553e52.jpg
         * location : http://e.vhall.com/538384425
         * title : 体育直播
         */

        private List<PicsBean> pics;

        public List<PicsBean> getPics() {
            return pics;
        }

        public void setPics(List<PicsBean> pics) {
            this.pics = pics;
        }

        public static class PicsBean {
            private String action;
            private String imgUrl;
            private String location;
            private String title;

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
