package com.atguigu.granaryaidi.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */

public class DaRenRecommendBean {


    /**
     * meta : {"status":0,"server_time":"2017-07-07 18:39:47","account_id":0,"cost":0.016102075576782,"errdata":null,"errmsg":""}
     * version : 1
     * data : {"has_more":true,"num_items":"10","items":{"user_id":"999999673","user_name":"Martin Parr (马格南主席）","is_daren":"1","email":"martinparr@iliangcang.com","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/3/999/999/999999673.jpg?t=1499423987","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/3/999/999/999999673.jpg?t=1499423987","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/3/999/999/999999673.jpg?t=1499423987"},"user_desc":"摄影师","friend":"0","like_count":"0","recommendation_count":"10","following_count":"0","followed_count":"857","template_id":"0","goods":[{"goods_id":"253151","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253151.jpg","goods_name":"240 Landscape","price":"354.90","owner_id":"999999673","comment_count":"4","is_outter":"1","like_count":"18","liked":"0"},{"goods_id":"253153","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253153.jpg","goods_name":"MAA","price":"412.67","owner_id":"999999673","comment_count":"7","is_outter":"1","like_count":"15","liked":"0"},{"goods_id":"253154","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253154.jpg","goods_name":"The Heavens: Annual Report","price":"286.20","owner_id":"999999673","comment_count":"2","is_outter":"1","like_count":"15","liked":"0"},{"goods_id":"253156","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253156.jpg","goods_name":"You Haven&#039;t Seen Their Faces","price":"2830.92","owner_id":"999999673","comment_count":"10","is_outter":"1","like_count":"13","liked":"0"},{"goods_id":"253157","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253157.jpg","goods_name":"Early Works","price":"186.65","owner_id":"999999673","comment_count":"5","is_outter":"1","like_count":"10","liked":"0"},{"goods_id":"253159","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253159.jpg","goods_name":"Cabanagem","price":"297.12","owner_id":"999999673","comment_count":"6","is_outter":"1","like_count":"18","liked":"0"},{"goods_id":"253160","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253160.jpg","goods_name":"Songbook","price":"447.97","owner_id":"999999673","comment_count":"9","is_outter":"1","like_count":"12","liked":"0"},{"goods_id":"253161","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253161.jpg","goods_name":"Until Death Do Us Part","price":"466.64","owner_id":"999999673","comment_count":"7","is_outter":"1","like_count":"26","liked":"0"},{"goods_id":"253162","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253162.jpg","goods_name":"Face","price":"348.15","owner_id":"999999673","comment_count":"20","is_outter":"1","like_count":"36","liked":"0"},{"goods_id":"253167","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253167.jpg","goods_name":"Waiting","price":"247.60","owner_id":"999999673","comment_count":"0","is_outter":"1","like_count":"3","liked":"0"}]}}
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
         * server_time : 2017-07-07 18:39:47
         * account_id : 0
         * cost : 0.016102075576782
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
         * has_more : true
         * num_items : 10
         * items : {"user_id":"999999673","user_name":"Martin Parr (马格南主席）","is_daren":"1","email":"martinparr@iliangcang.com","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/3/999/999/999999673.jpg?t=1499423987","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/3/999/999/999999673.jpg?t=1499423987","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/3/999/999/999999673.jpg?t=1499423987"},"user_desc":"摄影师","friend":"0","like_count":"0","recommendation_count":"10","following_count":"0","followed_count":"857","template_id":"0","goods":[{"goods_id":"253151","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253151.jpg","goods_name":"240 Landscape","price":"354.90","owner_id":"999999673","comment_count":"4","is_outter":"1","like_count":"18","liked":"0"},{"goods_id":"253153","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253153.jpg","goods_name":"MAA","price":"412.67","owner_id":"999999673","comment_count":"7","is_outter":"1","like_count":"15","liked":"0"},{"goods_id":"253154","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253154.jpg","goods_name":"The Heavens: Annual Report","price":"286.20","owner_id":"999999673","comment_count":"2","is_outter":"1","like_count":"15","liked":"0"},{"goods_id":"253156","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253156.jpg","goods_name":"You Haven&#039;t Seen Their Faces","price":"2830.92","owner_id":"999999673","comment_count":"10","is_outter":"1","like_count":"13","liked":"0"},{"goods_id":"253157","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253157.jpg","goods_name":"Early Works","price":"186.65","owner_id":"999999673","comment_count":"5","is_outter":"1","like_count":"10","liked":"0"},{"goods_id":"253159","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253159.jpg","goods_name":"Cabanagem","price":"297.12","owner_id":"999999673","comment_count":"6","is_outter":"1","like_count":"18","liked":"0"},{"goods_id":"253160","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253160.jpg","goods_name":"Songbook","price":"447.97","owner_id":"999999673","comment_count":"9","is_outter":"1","like_count":"12","liked":"0"},{"goods_id":"253161","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253161.jpg","goods_name":"Until Death Do Us Part","price":"466.64","owner_id":"999999673","comment_count":"7","is_outter":"1","like_count":"26","liked":"0"},{"goods_id":"253162","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253162.jpg","goods_name":"Face","price":"348.15","owner_id":"999999673","comment_count":"20","is_outter":"1","like_count":"36","liked":"0"},{"goods_id":"253167","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253167.jpg","goods_name":"Waiting","price":"247.60","owner_id":"999999673","comment_count":"0","is_outter":"1","like_count":"3","liked":"0"}]}
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
             * user_id : 999999673
             * user_name : Martin Parr (马格南主席）
             * is_daren : 1
             * email : martinparr@iliangcang.com
             * user_image : {"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/3/999/999/999999673.jpg?t=1499423987","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/3/999/999/999999673.jpg?t=1499423987","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/3/999/999/999999673.jpg?t=1499423987"}
             * user_desc : 摄影师
             * friend : 0
             * like_count : 0
             * recommendation_count : 10
             * following_count : 0
             * followed_count : 857
             * template_id : 0
             * goods : [{"goods_id":"253151","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253151.jpg","goods_name":"240 Landscape","price":"354.90","owner_id":"999999673","comment_count":"4","is_outter":"1","like_count":"18","liked":"0"},{"goods_id":"253153","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253153.jpg","goods_name":"MAA","price":"412.67","owner_id":"999999673","comment_count":"7","is_outter":"1","like_count":"15","liked":"0"},{"goods_id":"253154","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253154.jpg","goods_name":"The Heavens: Annual Report","price":"286.20","owner_id":"999999673","comment_count":"2","is_outter":"1","like_count":"15","liked":"0"},{"goods_id":"253156","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253156.jpg","goods_name":"You Haven&#039;t Seen Their Faces","price":"2830.92","owner_id":"999999673","comment_count":"10","is_outter":"1","like_count":"13","liked":"0"},{"goods_id":"253157","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253157.jpg","goods_name":"Early Works","price":"186.65","owner_id":"999999673","comment_count":"5","is_outter":"1","like_count":"10","liked":"0"},{"goods_id":"253159","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253159.jpg","goods_name":"Cabanagem","price":"297.12","owner_id":"999999673","comment_count":"6","is_outter":"1","like_count":"18","liked":"0"},{"goods_id":"253160","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253160.jpg","goods_name":"Songbook","price":"447.97","owner_id":"999999673","comment_count":"9","is_outter":"1","like_count":"12","liked":"0"},{"goods_id":"253161","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253161.jpg","goods_name":"Until Death Do Us Part","price":"466.64","owner_id":"999999673","comment_count":"7","is_outter":"1","like_count":"26","liked":"0"},{"goods_id":"253162","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253162.jpg","goods_name":"Face","price":"348.15","owner_id":"999999673","comment_count":"20","is_outter":"1","like_count":"36","liked":"0"},{"goods_id":"253167","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253167.jpg","goods_name":"Waiting","price":"247.60","owner_id":"999999673","comment_count":"0","is_outter":"1","like_count":"3","liked":"0"}]
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
            private List<GoodsBean> goods;

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

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class UserImageBean {
                /**
                 * self_img : 1
                 * orig : http://imgs-qn.iliangcang.com/ware/userhead/orig/3/999/999/999999673.jpg?t=1499423987
                 * mid : http://imgs-qn.iliangcang.com/ware/userhead/mid/3/999/999/999999673.jpg?t=1499423987
                 * tmb : http://imgs-qn.iliangcang.com/ware/userhead/tmb/3/999/999/999999673.jpg?t=1499423987
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

            public static class GoodsBean {
                /**
                 * goods_id : 253151
                 * goods_image : http://imgs-qn.iliangcang.com/ware/goods/big/2/253/253151.jpg
                 * goods_name : 240 Landscape
                 * price : 354.90
                 * owner_id : 999999673
                 * comment_count : 4
                 * is_outter : 1
                 * like_count : 18
                 * liked : 0
                 */

                private String goods_id;
                private String goods_image;
                private String goods_name;
                private String price;
                private String owner_id;
                private String comment_count;
                private String is_outter;
                private String like_count;
                private String liked;

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getGoods_image() {
                    return goods_image;
                }

                public void setGoods_image(String goods_image) {
                    this.goods_image = goods_image;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getOwner_id() {
                    return owner_id;
                }

                public void setOwner_id(String owner_id) {
                    this.owner_id = owner_id;
                }

                public String getComment_count() {
                    return comment_count;
                }

                public void setComment_count(String comment_count) {
                    this.comment_count = comment_count;
                }

                public String getIs_outter() {
                    return is_outter;
                }

                public void setIs_outter(String is_outter) {
                    this.is_outter = is_outter;
                }

                public String getLike_count() {
                    return like_count;
                }

                public void setLike_count(String like_count) {
                    this.like_count = like_count;
                }

                public String getLiked() {
                    return liked;
                }

                public void setLiked(String liked) {
                    this.liked = liked;
                }
            }
        }
    }
}
