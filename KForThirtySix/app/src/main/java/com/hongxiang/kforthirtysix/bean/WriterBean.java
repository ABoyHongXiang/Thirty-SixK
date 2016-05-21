package com.hongxiang.kforthirtysix.bean;

import java.util.List;

/**
 * Created by dllo on 16/5/21.
 */
public class WriterBean {

    /**
     * code : 0
     * data : {"uid":375349,"latestArticle":[{"summary":"草根自媒体现在可能遇到了自进入微信以来的最大难关。","publishTime":1463651577000,"updateTime":1463813248000,"columnId":70,"type":"article","featureImg":"","commentCount":1,"postId":5047187,"content":"","title":"草根自媒体正面临生死攸关的大门槛","myFavorites":false,"favoriteCount":29,"columnName":"深度","user":{"avatar":"https://krplus-pic.b0.upaiyun.com/201603/22055321/cg0huhoojxi0iipw.jpg!800","name":"36氪的朋友们","ssoId":2690},"viewCount":7485}],"totalCount":1599,"name":"36氪的朋友们","roleType":"author","totalView":56488086,"avatar":"https://krplus-pic.b0.upaiyun.com/201603/22055321/cg0huhoojxi0iipw.jpg!800","brief":"群众的智慧是无穷的~欢迎一线的创业者和投资者分享你们的观察和看法 tips@36kr.com"}
     * msg : 操作成功！
     */

    private int code;
    /**
     * uid : 375349
     * latestArticle : [{"summary":"草根自媒体现在可能遇到了自进入微信以来的最大难关。","publishTime":1463651577000,"updateTime":1463813248000,"columnId":70,"type":"article","featureImg":"","commentCount":1,"postId":5047187,"content":"","title":"草根自媒体正面临生死攸关的大门槛","myFavorites":false,"favoriteCount":29,"columnName":"深度","user":{"avatar":"https://krplus-pic.b0.upaiyun.com/201603/22055321/cg0huhoojxi0iipw.jpg!800","name":"36氪的朋友们","ssoId":2690},"viewCount":7485}]
     * totalCount : 1599
     * name : 36氪的朋友们
     * roleType : author
     * totalView : 56488086
     * avatar : https://krplus-pic.b0.upaiyun.com/201603/22055321/cg0huhoojxi0iipw.jpg!800
     * brief : 群众的智慧是无穷的~欢迎一线的创业者和投资者分享你们的观察和看法 tips@36kr.com
     */

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
        private int uid;
        private int totalCount;
        private String name;
        private String roleType;
        private int totalView;
        private String avatar;
        private String brief;
        /**
         * summary : 草根自媒体现在可能遇到了自进入微信以来的最大难关。
         * publishTime : 1463651577000
         * updateTime : 1463813248000
         * columnId : 70
         * type : article
         * featureImg :
         * commentCount : 1
         * postId : 5047187
         * content :
         * title : 草根自媒体正面临生死攸关的大门槛
         * myFavorites : false
         * favoriteCount : 29
         * columnName : 深度
         * user : {"avatar":"https://krplus-pic.b0.upaiyun.com/201603/22055321/cg0huhoojxi0iipw.jpg!800","name":"36氪的朋友们","ssoId":2690}
         * viewCount : 7485
         */

        private List<LatestArticleBean> latestArticle;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRoleType() {
            return roleType;
        }

        public void setRoleType(String roleType) {
            this.roleType = roleType;
        }

        public int getTotalView() {
            return totalView;
        }

        public void setTotalView(int totalView) {
            this.totalView = totalView;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public List<LatestArticleBean> getLatestArticle() {
            return latestArticle;
        }

        public void setLatestArticle(List<LatestArticleBean> latestArticle) {
            this.latestArticle = latestArticle;
        }

        public static class LatestArticleBean {
            private String summary;
            private long publishTime;
            private long updateTime;
            private int columnId;
            private String type;
            private String featureImg;
            private int commentCount;
            private int postId;
            private String content;
            private String title;
            private boolean myFavorites;
            private int favoriteCount;
            private String columnName;
            /**
             * avatar : https://krplus-pic.b0.upaiyun.com/201603/22055321/cg0huhoojxi0iipw.jpg!800
             * name : 36氪的朋友们
             * ssoId : 2690
             */

            private UserBean user;
            private int viewCount;

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public int getColumnId() {
                return columnId;
            }

            public void setColumnId(int columnId) {
                this.columnId = columnId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getFeatureImg() {
                return featureImg;
            }

            public void setFeatureImg(String featureImg) {
                this.featureImg = featureImg;
            }

            public int getCommentCount() {
                return commentCount;
            }

            public void setCommentCount(int commentCount) {
                this.commentCount = commentCount;
            }

            public int getPostId() {
                return postId;
            }

            public void setPostId(int postId) {
                this.postId = postId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public boolean isMyFavorites() {
                return myFavorites;
            }

            public void setMyFavorites(boolean myFavorites) {
                this.myFavorites = myFavorites;
            }

            public int getFavoriteCount() {
                return favoriteCount;
            }

            public void setFavoriteCount(int favoriteCount) {
                this.favoriteCount = favoriteCount;
            }

            public String getColumnName() {
                return columnName;
            }

            public void setColumnName(String columnName) {
                this.columnName = columnName;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public int getViewCount() {
                return viewCount;
            }

            public void setViewCount(int viewCount) {
                this.viewCount = viewCount;
            }

            public static class UserBean {
                private String avatar;
                private String name;
                private int ssoId;

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getSsoId() {
                    return ssoId;
                }

                public void setSsoId(int ssoId) {
                    this.ssoId = ssoId;
                }
            }
        }
    }
}
