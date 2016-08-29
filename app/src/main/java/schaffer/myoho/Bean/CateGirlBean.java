package schaffer.myoho.Bean;

import java.util.List;

/**
 * Created by a7352 on 2016/8/24.
 */
public class CateGirlBean {

    /**
     * sucessfully : ok
     * girl : [{"_id":"10","name":"裙装","SexId":"0"},{"_id":"11","name":"创意生活","SexId":"0"},{"_id":"12","name":"上衣","SexId":"0"},{"_id":"13","name":"裤子","SexId":"0"},{"_id":"14","name":"鞋子","SexId":"0"},{"_id":"15","name":"配置","SexId":"0"},{"_id":"16","name":"包类","SexId":"0"},{"_id":"17","name":"内衣家居服","SexId":"0"},{"_id":"18","name":"创意生活","SexId":"0"},{"_id":"19","name":"泳衣","SexId":"0"},{"_id":"20","name":"潮童","SexId":"0"}]
     */

    private String sucessfully;
    private List<GirlBean> girl;

    public void setSucessfully(String sucessfully) {
        this.sucessfully = sucessfully;
    }

    public void setGirl(List<GirlBean> girl) {
        this.girl = girl;
    }

    public String getSucessfully() {
        return sucessfully;
    }

    public List<GirlBean> getGirl() {
        return girl;
    }

    public static class GirlBean {
        /**
         * _id : 10
         * name : 裙装
         * SexId : 0
         */

        private String _id;
        private String name;
        private String SexId;

        public void set_id(String _id) {
            this._id = _id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSexId(String SexId) {
            this.SexId = SexId;
        }

        public String get_id() {
            return _id;
        }

        public String getName() {
            return name;
        }

        public String getSexId() {
            return SexId;
        }
    }
}
