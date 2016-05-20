package com.hongxiang.kforthirtysix.bean;

import java.util.List;

/**
 * Created by dllo on 16/5/20.
 */
public class LolAllBean {
    /**
     * data : [{"id":"65966","title":"幽梦+黑切到底强在哪里？","desc":"卢锡安崛起的思考：它们的特点与优点在哪，为你一一解答","video_url":"","published":1463740233,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"65961","title":"6.10关键改动解析：乌鸦或成最大赢家","desc":"都说一代补丁一代神，那么6.10中哪些改动的影响比较大呢？","video_url":"","published":1463728805,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"65955","title":"LOL掌游宝好声音大赛！丰富大奖等你来拿","desc":"参与活动不仅有奖品可以拿，还会制作成MV，发布在LOL掌游宝置顶头条哦~","video_url":"","published":1463722249,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"65948","title":"国服5月20日至5月23日限时折扣","desc":"本周也是6个英雄6款皮肤半价","video_url":"","published":1463714286,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"65938","title":"外服英雄熟练度系统开放6、7等级","desc":"拳头官方宣布在外服开启英雄熟练度系统的6级以及7级。","video_url":"","published":1463710909,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"65929","title":"测试服5月20日：艾克、劫遭到削弱","desc":"艾克被动不再减速目标，劫大招AD加成砍了一半...","video_url":"","published":1463708367,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"65927","title":"5月20日吉格斯的模式实验室：飞升模式！","desc":"围绕地图BOSS泽拉斯身上的飞升之力，与对方队伍展开斗智斗勇的较量","video_url":"","published":1463654218,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"65925","title":"设计师答疑：新英雄与新三相解读","desc":"看上去琴女本应该在测试服中回调的改动被实装到了正式服中。","video_url":"","published":1463650791,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"65916","title":"掉线重新开局将上线！新系统详细说明 ","desc":"允许玩家在出现4v5的情况下，在开局的时候发起重开比赛的投票","video_url":"","published":1463646642,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123},{"id":"65884","title":"设计师解答：劫与索尔可能会继续改动","desc":"目前劫的禁用率高的原因是很多玩家习惯性的去禁用这个英雄，而非他本身过强。","video_url":"","published":1463627494,"weight":"0","platform":"1","pic_url":"","recommend":0,"w":123}]
     * code : 200
     * message : ok
     * api : 1
     */

    private int code;
    private String message;
    private int api;
    /**
     * id : 65966
     * title : 幽梦+黑切到底强在哪里？
     * desc : 卢锡安崛起的思考：它们的特点与优点在哪，为你一一解答
     * video_url :
     * published : 1463740233
     * weight : 0
     * platform : 1
     * pic_url :
     * recommend : 0
     * w : 123
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getApi() {
        return api;
    }

    public void setApi(int api) {
        this.api = api;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String title;
        private String desc;
        private String video_url;
        private int published;
        private String weight;
        private String platform;
        private String pic_url;
        private int recommend;
        private int w;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public int getPublished() {
            return published;
        }

        public void setPublished(int published) {
            this.published = published;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public int getRecommend() {
            return recommend;
        }

        public void setRecommend(int recommend) {
            this.recommend = recommend;
        }

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }
    }
}
