package com.hongxiang.kforthirtysix.bean;


import java.util.List;

/**
 * Created by dllo on 16/5/13.
 */
public class TvBean {
    private int code;
    private DataBean data;
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
    public static class DataBean {
        private int page;
        private int totalCount;
        private List<InDataBean> data;
        public int getPage() {
            return page;
        }
        public void setPage(int page) {
            this.page = page;
        }
        public int getTotalCount() {
            return totalCount;
        }
        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }
        public List<InDataBean> getData() {
            return data;
        }
        public void setData(List<InDataBean> data) {
            this.data = data;
        }
        public static class InDataBean {
            private InTvBean tv;
            private String columnName;
            private String columnId;
            private String type;
            public InTvBean getTv() {
                return tv;
            }
            public void setTv(InTvBean tv) {
                this.tv = tv;
            }
            public String getColumnName() {
                return columnName;
            }
            public void setColumnName(String columnName) {
                this.columnName = columnName;
            }
            public String getColumnId() {
                return columnId;
            }
            public void setColumnId(String columnId) {
                this.columnId = columnId;
            }
            public String getType() {
                return type;
            }
            public void setType(String type) {
                this.type = type;
            }
            public static class InTvBean {
                private String videoSource360;
                private String id;
                private String videoSource720;
                private String title;
                private String duration;
                private String videoSource;
                private long publishTime;
                private String youkuUrl;
                private String videoSource480;
                private String featureImg;
                private int durationLong;
                public String getVideoSource360() {
                    return videoSource360;
                }
                public void setVideoSource360(String videoSource360) {
                    this.videoSource360 = videoSource360;
                }
                public String getId() {
                    return id;
                }
                public void setId(String id) {
                    this.id = id;
                }
                public String getVideoSource720() {
                    return videoSource720;
                }
                public void setVideoSource720(String videoSource720) {
                    this.videoSource720 = videoSource720;
                }
                public String getTitle() {
                    return title;
                }
                public void setTitle(String title) {
                    this.title = title;
                }
                public String getDuration() {
                    return duration;
                }
                public void setDuration(String duration) {
                    this.duration = duration;
                }
                public String getVideoSource() {
                    return videoSource;
                }
                public void setVideoSource(String videoSource) {
                    this.videoSource = videoSource;
                }
                public long getPublishTime() {
                    return publishTime;
                }
                public void setPublishTime(long publishTime) {
                    this.publishTime = publishTime;
                }
                public String getYoukuUrl() {
                    return youkuUrl;
                }
                public void setYoukuUrl(String youkuUrl) {
                    this.youkuUrl = youkuUrl;
                }
                public String getVideoSource480() {
                    return videoSource480;
                }
                public void setVideoSource480(String videoSource480) {
                    this.videoSource480 = videoSource480;
                }
                public String getFeatureImg() {
                    return featureImg;
                }
                public void setFeatureImg(String featureImg) {
                    this.featureImg = featureImg;
                }
                public int getDurationLong() {
                    return durationLong;
                }
                public void setDurationLong(int durationLong) {
                    this.durationLong = durationLong;
                }
            }
        }
    }
}
