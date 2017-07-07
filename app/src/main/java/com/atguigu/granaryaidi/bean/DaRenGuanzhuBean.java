package com.atguigu.granaryaidi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public class DaRenGuanzhuBean {


    /**
     * meta : {"status":0,"server_time":"2017-07-07 21:15:44","account_id":0,"cost":0.011275053024292,"errdata":null,"errmsg":""}
     * version : 1
     * data : {"has_more":false,"num_items":"5","items":{"user_id":"8051","user_name":"韩寒","is_daren":"1","email":"hanhan@126.com","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/8/8051.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/8/8051.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/8/8051.jpg?t=1499433344"},"user_desc":"作家 赛车手","friend":"0","like_count":"0","recommendation_count":"3","following_count":"5","followed_count":"11815","template_id":"0","users":[{"user_id":"21996","user_name":"慎展鹏\n","is_daren":"0","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/21/21996.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/21/21996.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/21/21996.jpg?t=1499433344"},"user_desc":""},{"user_id":"21891","user_name":"Rick\n","is_daren":"0","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/21/21891.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/21/21891.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/21/21891.jpg?t=1499433344"},"user_desc":""},{"user_id":"21617","user_name":"Febe\n","is_daren":"0","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/21/21617.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/21/21617.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/21/21617.jpg?t=1499433344"},"user_desc":""},{"user_id":"20885","user_name":"伍博文\n","is_daren":"0","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/20/20885.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/20/20885.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/20/20885.jpg?t=1499433344"},"user_desc":""},{"user_id":"18122","user_name":"娄悠\r\n","is_daren":"0","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/18/18122.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/18/18122.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/18/18122.jpg?t=1499433344"},"user_desc":""}]}}
     */

    private MetaBean meta;
    private int version;
    private DataBean data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * status : 0
         * server_time : 2017-07-07 21:15:44
         * account_id : 0
         * cost : 0.011275053024292
         * errdata : null
         * errmsg :
         */

        private int status;
        private String server_time;
        private int account_id;
        private double cost;
        private Object errdata;
        private String errmsg;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getServer_time() {
            return server_time;
        }

        public void setServer_time(String server_time) {
            this.server_time = server_time;
        }

        public int getAccount_id() {
            return account_id;
        }

        public void setAccount_id(int account_id) {
            this.account_id = account_id;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public Object getErrdata() {
            return errdata;
        }

        public void setErrdata(Object errdata) {
            this.errdata = errdata;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }
    }

    public static class DataBean {
        /**
         * has_more : false
         * num_items : 5
         * items : {"user_id":"8051","user_name":"韩寒","is_daren":"1","email":"hanhan@126.com","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/8/8051.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/8/8051.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/8/8051.jpg?t=1499433344"},"user_desc":"作家 赛车手","friend":"0","like_count":"0","recommendation_count":"3","following_count":"5","followed_count":"11815","template_id":"0","users":[{"user_id":"21996","user_name":"慎展鹏\n","is_daren":"0","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/21/21996.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/21/21996.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/21/21996.jpg?t=1499433344"},"user_desc":""},{"user_id":"21891","user_name":"Rick\n","is_daren":"0","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/21/21891.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/21/21891.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/21/21891.jpg?t=1499433344"},"user_desc":""},{"user_id":"21617","user_name":"Febe\n","is_daren":"0","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/21/21617.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/21/21617.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/21/21617.jpg?t=1499433344"},"user_desc":""},{"user_id":"20885","user_name":"伍博文\n","is_daren":"0","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/20/20885.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/20/20885.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/20/20885.jpg?t=1499433344"},"user_desc":""},{"user_id":"18122","user_name":"娄悠\r\n","is_daren":"0","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/18/18122.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/18/18122.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/18/18122.jpg?t=1499433344"},"user_desc":""}]}
         */

        private boolean has_more;
        private String num_items;
        private ItemsBean items;

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public String getNum_items() {
            return num_items;
        }

        public void setNum_items(String num_items) {
            this.num_items = num_items;
        }

        public ItemsBean getItems() {
            return items;
        }

        public void setItems(ItemsBean items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * user_id : 8051
             * user_name : 韩寒
             * is_daren : 1
             * email : hanhan@126.com
             * user_image : {"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/8/8051.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/8/8051.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/8/8051.jpg?t=1499433344"}
             * user_desc : 作家 赛车手
             * friend : 0
             * like_count : 0
             * recommendation_count : 3
             * following_count : 5
             * followed_count : 11815
             * template_id : 0
             * users : [{"user_id":"21996","user_name":"慎展鹏\n","is_daren":"0","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/21/21996.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/21/21996.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/21/21996.jpg?t=1499433344"},"user_desc":""},{"user_id":"21891","user_name":"Rick\n","is_daren":"0","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/21/21891.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/21/21891.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/21/21891.jpg?t=1499433344"},"user_desc":""},{"user_id":"21617","user_name":"Febe\n","is_daren":"0","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/21/21617.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/21/21617.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/21/21617.jpg?t=1499433344"},"user_desc":""},{"user_id":"20885","user_name":"伍博文\n","is_daren":"0","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/20/20885.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/20/20885.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/20/20885.jpg?t=1499433344"},"user_desc":""},{"user_id":"18122","user_name":"娄悠\r\n","is_daren":"0","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/18/18122.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/18/18122.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/18/18122.jpg?t=1499433344"},"user_desc":""}]
             */

            private String user_id;
            private String user_name;
            private String is_daren;
            private String email;
            private UserImageBean user_image;
            private String user_desc;
            private String friend;
            private String like_count;
            private String recommendation_count;
            private String following_count;
            private String followed_count;
            private String template_id;
            private List<UsersBean> users;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getIs_daren() {
                return is_daren;
            }

            public void setIs_daren(String is_daren) {
                this.is_daren = is_daren;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public UserImageBean getUser_image() {
                return user_image;
            }

            public void setUser_image(UserImageBean user_image) {
                this.user_image = user_image;
            }

            public String getUser_desc() {
                return user_desc;
            }

            public void setUser_desc(String user_desc) {
                this.user_desc = user_desc;
            }

            public String getFriend() {
                return friend;
            }

            public void setFriend(String friend) {
                this.friend = friend;
            }

            public String getLike_count() {
                return like_count;
            }

            public void setLike_count(String like_count) {
                this.like_count = like_count;
            }

            public String getRecommendation_count() {
                return recommendation_count;
            }

            public void setRecommendation_count(String recommendation_count) {
                this.recommendation_count = recommendation_count;
            }

            public String getFollowing_count() {
                return following_count;
            }

            public void setFollowing_count(String following_count) {
                this.following_count = following_count;
            }

            public String getFollowed_count() {
                return followed_count;
            }

            public void setFollowed_count(String followed_count) {
                this.followed_count = followed_count;
            }

            public String getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(String template_id) {
                this.template_id = template_id;
            }

            public List<UsersBean> getUsers() {
                return users;
            }

            public void setUsers(List<UsersBean> users) {
                this.users = users;
            }

            public static class UserImageBean {
                /**
                 * self_img : 1
                 * orig : http://imgs-qn.iliangcang.com/ware/userhead/orig/2/8/8051.jpg?t=1499433344
                 * mid : http://imgs-qn.iliangcang.com/ware/userhead/mid/2/8/8051.jpg?t=1499433344
                 * tmb : http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/8/8051.jpg?t=1499433344
                 */

                private String self_img;
                private String orig;
                private String mid;
                private String tmb;

                public String getSelf_img() {
                    return self_img;
                }

                public void setSelf_img(String self_img) {
                    this.self_img = self_img;
                }

                public String getOrig() {
                    return orig;
                }

                public void setOrig(String orig) {
                    this.orig = orig;
                }

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
                }

                public String getTmb() {
                    return tmb;
                }

                public void setTmb(String tmb) {
                    this.tmb = tmb;
                }
            }

            public static class UsersBean {
                /**
                 * user_id : 21996
                 * user_name : 慎展鹏

                 * is_daren : 0
                 * user_image : {"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/21/21996.jpg?t=1499433344","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/21/21996.jpg?t=1499433344","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/21/21996.jpg?t=1499433344"}
                 * user_desc :
                 */

                private String user_id;
                private String user_name;
                private String is_daren;
                private UserImageBeanX user_image;
                private String user_desc;

                public String getUser_id() {
                    return user_id;
                }

                public void setUser_id(String user_id) {
                    this.user_id = user_id;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public String getIs_daren() {
                    return is_daren;
                }

                public void setIs_daren(String is_daren) {
                    this.is_daren = is_daren;
                }

                public UserImageBeanX getUser_image() {
                    return user_image;
                }

                public void setUser_image(UserImageBeanX user_image) {
                    this.user_image = user_image;
                }

                public String getUser_desc() {
                    return user_desc;
                }

                public void setUser_desc(String user_desc) {
                    this.user_desc = user_desc;
                }

                public static class UserImageBeanX {
                    /**
                     * self_img : 1
                     * orig : http://imgs-qn.iliangcang.com/ware/userhead/orig/2/21/21996.jpg?t=1499433344
                     * mid : http://imgs-qn.iliangcang.com/ware/userhead/mid/2/21/21996.jpg?t=1499433344
                     * tmb : http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/21/21996.jpg?t=1499433344
                     */

                    private String self_img;
                    private String orig;
                    private String mid;
                    private String tmb;

                    public String getSelf_img() {
                        return self_img;
                    }

                    public void setSelf_img(String self_img) {
                        this.self_img = self_img;
                    }

                    public String getOrig() {
                        return orig;
                    }

                    public void setOrig(String orig) {
                        this.orig = orig;
                    }

                    public String getMid() {
                        return mid;
                    }

                    public void setMid(String mid) {
                        this.mid = mid;
                    }

                    public String getTmb() {
                        return tmb;
                    }

                    public void setTmb(String tmb) {
                        this.tmb = tmb;
                    }
                }
            }
        }
    }
}
