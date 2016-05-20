package com.hongxiang.kforthirtysix.bean;

import java.util.List;

/**
 * Created by dllo on 16/5/20.
 */
public class LolDetailsBean {

    /**
     * data : [{"id":"29859","item_id":"65962","title":"小漠集锦 第八十期","desc":"BoxBox手柄秀锐雯！","published":1463734800,"video_url":"http://pl.youku.com/playlist/m3u8?ts=1463737724&keyframe=0&pid=6b5f94f4ab33c702&vid=XMTU3NzUxNTE3Mg==&type=flv&r=/3sLngL0Q6CXymAIiF9JUQQtnOFNJPUClO8A56KJJcT8UB+NRAMQ09zE6rNj4EKMxAvRByWf6hitgv75Fv0ffULWfTAr05EJys0NuTHSVHRU/l3ZcBOSsxUf7QaPO6gDptAU4mTDRr+dVJThYEJUntRo2qWDdSLEtlRAR/RZSfXulG3IX9jfwRi4lEtzRfCf&ypremium=1&oip=2340490781&token=4661&sid=64637377246972097b731&did=1463737724&ev=1&ctype=20&ep=FpxLkDD5dfmD00aQm1ft86fPSX5eu8a9tIgj8sXuCHZYNEpVhnq7h8S83Z3XNcuo&yxon=1&special=true","status":"1","pic_url":"http://avatar.anzogame.com/pic_v1/lol/news/20160520/spic65962h573ece35.jpg"},{"id":"29857","item_id":"65960","title":"老三星重聚韩服 然而翻车啦","desc":"DADE MATA LOOPER HEART","published":1463727599,"video_url":"http://k.youku.com/player/getFlvPath/sid/74637407630662068c563_00/st/mp4/fileid/0300200100573E822ACDCE0410ACE6D0E24BBC-A7AC-6B8A-53C8-B3009F17852F?k=317416fa0568076b24129cc7&hd=1&myp=0&ts=1460&r=/3sLngL0Q6CXymAIiF9JUQQtnOFNJPUClO8A56KJJcT8UB+NRAMQ09zE6rNj4EKMxAvRByWf6hitgv75Fv0ffV/NL/bW7xZLAh04BFNxE8jq6wn5PT9OIoC0LRyh94oqxqRtrVO76L4PtFKXqM2Uv6oiee/VjpMkQyKQmzXb5+8=&ypremium=1&oip=2340490781&token=6218&sid=74637407630662068c563&did=1463740762&ev=1&ctype=20&ep=%2BDbL2NUvRs9J76WxkUhqooOBK5ffTh%2BxuSw3ENTy6dn00VUh%2Fx%2FX31bC6XSU%2B0Ex59VqhW7VKpWXYCP6jwLiHqgvqpw9yZHiFIg%2BIasO%2BrWilqCWmv5Jermnqjvai70N","status":"1","pic_url":"http://avatar.anzogame.com/pic_v1/lol/news/20160520/spic65960h573eb4dd.jpg"},{"id":"29853","item_id":"65945","title":"神探苍教你玩 手把手教你末日","desc":"大5人","published":1463715366,"video_url":"http://k.youku.com/player/getFlvPath/sid/646373543746620b2b1bc_00/st/mp4/fileid/0300200100573DA816C5190572721A4834BD68-BC05-636B-5FDC-56AA47CB29AE?k=5d68ef7ed8b2072d24129cc2&hd=1&myp=0&ts=1317&r=/3sLngL0Q6CXymAIiF9JUQQtnOFNJPUClO8A56KJJcT8UB+NRAMQ09zE6rNj4EKMxAvRByWf6hitgv75Fv0ffaBadOh+CC0cP93G9+sdoqA3MrJPdmVptx+h3pG4dJ1VgPhRek13b6AjMHkiwns9j6oiee/VjpMkQyKQmzXb5+8=&ypremium=1&oip=2340490781&token=8916&sid=646373543746620b2b1bc&did=1463735437&ev=1&ctype=20&ep=nTIm61qY4ebYWYXMkUMMSGIlTOMb90VNdfX6K7CHW98UYWrXxrO91Gb%2FgwSsb0THQ8Hpo7P15jrqrtCEc9P5LVoMQ1hBsPJZunQxLl5kp4PjibK0mVlE5sxH2z%2Bta6wk","status":"1","pic_url":"http://avatar.anzogame.com/pic_v1/lol/news/20160520/spic65945h573e7b9f.jpg"},{"id":"29852","item_id":"65944","title":"Miss排位日记 男枪24杀","desc":"真猛男 格雷福斯24杀显豪情！","published":1463715366,"video_url":"http://k.youku.com/player/getFlvPath/sid/546373836162220e5a767_00/st/mp4/fileid/0300200100573EA26A24B22D9B7D2FF1DC979B-4270-0D40-6465-9333FD3D6027?k=16149675cb60cfc6282b6dc9&hd=1&myp=0&ts=3524&r=/3sLngL0Q6CXymAIiF9JUQQtnOFNJPUClO8A56KJJcT8UB+NRAMQ09zE6rNj4EKMxAvRByWf6hitgv75Fv0ffVPWDDMTfLHcs/AfHojfWHTbNXBE7ZyNwXQzQId/N6QuNPsQixReK+xfdh3V9yTVmaoiee/VjpMkQyKQmzXb5+8=&ypremium=1&oip=2340490781&token=6922&sid=546373836162220e5a767&did=1463738361&ev=1&ctype=20&ep=DrEVj8AaORM047Wl0dbVlYcwv1ARtqe8WZLeh5EZ3s2QsOd6ZLnzdKEQ32tcF6L7rHdcgQ9unBI40a8YjIhnsRqq%2FxG0OxbmMctV6JY5pHODSngJ9DSeCORxB1Kt%2FYAm","status":"1","pic_url":"http://avatar.anzogame.com/pic_v1/lol/news/20160520/spic65944h573e7b52.jpg"},{"id":"29842","item_id":"65930","title":"破败幻影诺手！","desc":"蛋蛋奇葩系列：光速血怒无人能敌！","published":1463707845,"video_url":"http://k.youku.com/player/getFlvPath/sid/4463732387582203c6c85_00/st/mp4/fileid/0300200100573E1603987C0343A591CB0D97E8-C78A-2D86-803A-0589312F5ED0?k=e0790195941b0900282b6dbe&hd=1&myp=0&ts=2306&r=/3sLngL0Q6CXymAIiF9JUQQtnOFNJPUClO8A56KJJcT8UB+NRAMQ09zE6rNj4EKMxAvRByWf6hitgv75Fv0ffQ+yg0Cgc/xJaJE709A7K/HtYhH+R8RrieBv3eZHKrdWfBC2A1KLR+DSFPC7XW9Uu6oiee/VjpMkQyKQmzXb5+8=&ypremium=1&oip=2340490781&token=1514&sid=4463732387582203c6c85&did=1463732387&ev=1&ctype=20&ep=FSbc31FOyLbUiBjNDBGTuhGtB37rWMzFcOCGsLQLpHxmIRb74VjxGcGZcwV0%2BqFM%2FNGoKmn9panSmQKHgMd8Xprq1yXZle%2FZBn7%2FlMRcV5IraLuf9X7Fv%2BU%2F5Zx9ZYBZ","status":"1","pic_url":"http://avatar.anzogame.com/pic_v1/lol/news/20160520/spic65930h573e6a6c.jpg"},{"id":"29844","item_id":"65932","title":"《天策的皮肤计划》第2期","desc":"无限烈焰皎月","published":1463707844,"video_url":"http://k.youku.com/player/getFlvPath/sid/746373414675120db77e1_00/st/mp4/fileid/0300200100573DA93A3070033A444B1C90B351-DAA8-F552-D0F6-87ABCB5AC959?k=787f59ee7828a1d4282b6dc2&hd=1&myp=0&ts=2272&r=/3sLngL0Q6CXymAIiF9JUQQtnOFNJPUClO8A56KJJcT8UB+NRAMQ09zE6rNj4EKMxAvRByWf6hitgv75Fv0ffRakl3YEy6PMqbrVcf49bIGsFPdatQNuoe8nMmTxQRu+kuo2eLiim8Gj/7OnYlmVw6oiee/VjpMkQyKQmzXb5+8=&ypremium=1&oip=2340490781&token=5454&sid=746373414675120db77e1&did=1463734146&ev=1&ctype=20&ep=8UAi8pz3705NueyJ1hBg8NZuU%2BBY78ADqNYwko3qLt9e79lQPyQ83BLwvNzVI2M8sgO6zxIJ0iHFqLcE0xgTPR6hOWzbgMsI2WWCJ78IGuo8nwFClhZMVbxS9Bn5fGZt","status":"1","pic_url":"http://avatar.anzogame.com/pic_v1/lol/news/20160520/spic65932h573e6aac.jpg"},{"id":"29839","item_id":"65920","title":"Uzi 薇恩超激王者大战","desc":"RNG豪车,超激王者大战30分钟近百人头","published":1463648400,"video_url":"http://k.youku.com/player/getFlvPath/sid/94637382605432013f8fd_00/st/mp4/fileid/0300200100573D5CB140C02DE7FDB12124699A-AE68-15EF-09D3-4BE2C7284143?k=7068c80ee765f7b524129cc4&hd=1&myp=0&ts=2108&r=/3sLngL0Q6CXymAIiF9JUQQtnOFNJPUClO8A56KJJcT8UB+NRAMQ09zE6rNj4EKMxAvRByWf6hitgv75Fv0ffR+CW1WBrtVefa11MVCN+Y+fQmyBn4rTTOCHA50N06N5YlEhYL+PfhMPkzqy8ph2yqoiee/VjpMkQyKQmzXb5+8=&ypremium=1&oip=2340490781&token=1194&sid=94637382605432013f8fd&did=1463738260&ev=1&ctype=20&ep=JZypWVilD4ZsoOvOnQ8Q5wqJfAJrDNaLN7Z%2F9J5CP0RyDzm9sGsTxS7meHxDURaGBDuBA6CAvwFF8zIsRK9qBmaPdyUjedeP9z2AayoZTRj7PFdN4Am8I7qhh6zxLihK","status":"1","pic_url":"http://avatar.anzogame.com/pic_v1/lol/news/20160519/spic65920h573d7dd8.jpg"},{"id":"29834","item_id":"65905","title":"韩服王者女警 6.9玩法详解","desc":"6.9出装技能天赋后期夹子释放说明","published":1463629091,"video_url":"http://k.youku.com/player/getFlvPath/sid/046374190720720f3090e_00/st/mp4/fileid/0300200100573C01CC6DBA2DE7FDB13CE7E226-4DA3-BF3C-DDAB-0465DEC45E1E?k=59a1a19e199136ef261f054d&hd=1&myp=0&ts=1518&r=/3sLngL0Q6CXymAIiF9JUQQtnOFNJPUClO8A56KJJcT8UB+NRAMQ09zE6rNj4EKMxAvRByWf6hitgv75Fv0ffQZotpzxEKc0qVH2qfG9R7BGdsKVU1jyqxEkpL4Ljde2vwgygj3FH/ATmni2Uoepxaoiee/VjpMkQyKQmzXb5+8=&ypremium=1&oip=2340490781&token=4459&sid=046374190720720f3090e&did=1463741907&ev=1&ctype=20&ep=Ts1birvRFTr751CeWG%2B9Ja%2FLf3tG3TC%2FvJmrQPsXbsE%2BIhUY1%2FCrMgRY2B4Ul14AMIiO3nQWrCrFv9KznKssW0KZEz%2BAMXilAGfYmhFH%2BUQpcvz5PTeaCnsKPDMFh8B3","status":"1","pic_url":"http://avatar.anzogame.com/pic_v1/lol/news/20160519/spic65905h573d3375.jpg"},{"id":"29835","item_id":"65906","title":"Gripex 盲僧6.10版本打野","desc":"第一视角 VS 悟空","published":1463629090,"video_url":"http://k.youku.com/player/getFlvPath/sid/146373881683220519a61_00/st/mp4/fileid/0300200100573CAD94B52F02A0153838D620CE-94E5-2BBA-0B35-14998C94DCA1?k=c76714802a602b26282b6dca&hd=1&myp=0&ts=2069&r=/3sLngL0Q6CXymAIiF9JUQQtnOFNJPUClO8A56KJJcT8UB+NRAMQ09zE6rNj4EKMxAvRByWf6hitgv75Fv0ffYdxB+a4df+drEqvI3mJ7XHfipoS68eEYsCRKKjqkFcmgk6keyb3TD/xcNeHz9r+zaoiee/VjpMkQyKQmzXb5+8=&ypremium=1&oip=2340490781&token=1548&sid=146373881683220519a61&did=1463738816&ev=1&ctype=20&ep=PRgfEfj0pHT8FTDrq5%2FGC1hn%2Ft54dXKyurKliap2VXV19IJUEY6OTPM7BZ2sYikN6x3yuNZPNgD9o0lghaLQeqceyLc%2FqafjnXuTiS2XzVas3Z6XYZG2OSkHGLzO%2BBkp","status":"1","pic_url":"http://avatar.anzogame.com/pic_v1/lol/news/20160519/spic65906h573d33af.jpg"},{"id":"29823","item_id":"65897","title":"韩服大师71%胜率安妮","desc":"原来法坦才是新王道","published":1463621457,"video_url":"http://k.youku.com/player/getFlvPath/sid/546372990357920d2d673_00/st/mp4/fileid/0300200100573C041AB21E2DE7FDB16215875F-960A-717F-F4CC-59D92C790F22?k=d385c805f79eb58e261f053c&hd=1&myp=0&ts=1487&r=/3sLngL0Q6CXymAIiF9JUQQtnOFNJPUClO8A56KJJcT8UB+NRAMQ09zE6rNj4EKMxAvRByWf6hitgv75Fv0ffVBV2G8N823WZLRt2jzAKvIRwtflT+lZvdURsZGGXS548T5xy81nKaq0/1+hiopwLqoiee/VjpMkQyKQmzXb5+8=&ypremium=1&oip=2340490781&token=1349&sid=546372990357920d2d673&did=1463729903&ev=1&ctype=20&ep=%2FWMzscSm8nTtETX4MPpcLEtgQ7kkBZrk3uMpYY%2BmdZDc1fqMP751jcAZo5QMXOXuydFH2aykqe8ev1bvRDsDIlHYB%2B3xr2M2ttQY7FKgb8Y41v9aV7gsW1ZdredTK%2Bvr","status":"1","pic_url":"http://avatar.anzogame.com/pic_v1/lol/news/20160519/spic65897h573d19de.jpg"}]
     * code : 200
     * message : ok
     * api : 1
     */

    private int code;
    private String message;
    private int api;
    /**
     * id : 29859
     * item_id : 65962
     * title : 小漠集锦 第八十期
     * desc : BoxBox手柄秀锐雯！
     * published : 1463734800
     * video_url : http://pl.youku.com/playlist/m3u8?ts=1463737724&keyframe=0&pid=6b5f94f4ab33c702&vid=XMTU3NzUxNTE3Mg==&type=flv&r=/3sLngL0Q6CXymAIiF9JUQQtnOFNJPUClO8A56KJJcT8UB+NRAMQ09zE6rNj4EKMxAvRByWf6hitgv75Fv0ffULWfTAr05EJys0NuTHSVHRU/l3ZcBOSsxUf7QaPO6gDptAU4mTDRr+dVJThYEJUntRo2qWDdSLEtlRAR/RZSfXulG3IX9jfwRi4lEtzRfCf&ypremium=1&oip=2340490781&token=4661&sid=64637377246972097b731&did=1463737724&ev=1&ctype=20&ep=FpxLkDD5dfmD00aQm1ft86fPSX5eu8a9tIgj8sXuCHZYNEpVhnq7h8S83Z3XNcuo&yxon=1&special=true
     * status : 1
     * pic_url : http://avatar.anzogame.com/pic_v1/lol/news/20160520/spic65962h573ece35.jpg
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
        private String item_id;
        private String title;
        private String desc;
        private int published;
        private String video_url;
        private String status;
        private String pic_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
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

        public int getPublished() {
            return published;
        }

        public void setPublished(int published) {
            this.published = published;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }
    }
}
