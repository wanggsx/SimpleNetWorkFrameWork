package com.wanggsx.networkframework

/**
 * 获取首页数据
 */
class UserBean : BaseModel() {

    /**
     * data : {"list":[{"type_id":"60","typename":"食品","typeimg":"","sub":[{"type_id":"62","typename":"肉食","typeimg":""},{"type_id":"61","typename":"面食","typeimg":""}],"banners1":[{"banner_id":"5","banner_url":"http://wangshijie.oss-cn-beijing.aliyuncs.com/20190212/141516_600938.jpg","target_type":"goods_details","target_url":"http://www.jd.com"}],"banners2":[{"banner_id":"6","banner_url":"http://wangshijie.oss-cn-beijing.aliyuncs.com/20190212/144347_744384.png","target_type":"goods_details","target_url":"http://www.baidu.com"}]},{"type_id":"59","typename":"母婴","typeimg":"","sub":[{"type_id":"64","typename":"婴儿床","typeimg":""},{"type_id":"63","typename":"奶粉","typeimg":""}],"banners1":[{"banner_id":"4","banner_url":"http://wangshijie.oss-cn-beijing.aliyuncs.com/20190130/152258_678782.jpg","target_type":"h5_url","target_url":""},{"banner_id":"1","banner_url":"http://wangshijie.oss-cn-beijing.aliyuncs.com/20190130/220620_561469.jpg","target_type":"goods_details","target_url":""}],"banners2":[]}]}
     */

    var data: DataBean? = null

    class DataBean {
        var search_placeholder: String? = null
        var list: List<TabBean>? = null

        class TabBean {
            /**
             * type_id : 60
             * typename : 食品
             * typeimg :
             * sub : [{"type_id":"62","typename":"肉食","typeimg":""},{"type_id":"61","typename":"面食","typeimg":""}]
             * banners1 : [{"banner_id":"5","banner_url":"http://wangshijie.oss-cn-beijing.aliyuncs.com/20190212/141516_600938.jpg","target_type":"goods_details","target_url":"http://www.jd.com"}]
             * banners2 : [{"banner_id":"6","banner_url":"http://wangshijie.oss-cn-beijing.aliyuncs.com/20190212/144347_744384.png","target_type":"goods_details","target_url":"http://www.baidu.com"}]
             */

            var type_id: String? = null
            var typename: String? = null
            var typeimg: String? = null
            var sub: List<SubBean>? = null
            var banners1: List<BannerBean>? = null
            var banners2: List<BannerBean>? = null

            class SubBean {
                /**
                 * type_id : 62
                 * typename : 肉食
                 * typeimg :
                 */

                var type_id: String? = null
                var typename: String? = null
                var typeimg: String? = null
                var target_url: String? = null
            }

            class BannerBean {
                /**
                 * banner_id : 5
                 * banner_url : http://wangshijie.oss-cn-beijing.aliyuncs.com/20190212/141516_600938.jpg
                 * target_type : goods_details
                 * target_url : http://www.jd.com
                 */

                var banner_id: String? = null
                var banner_url: String? = null
                var target_type: String? = null
                var target_url: String? = null
            }
        }
    }
}
