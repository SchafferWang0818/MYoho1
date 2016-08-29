package schaffer.myoho.Bean;

import java.util.List;

/**
 * Created by a7352 on 2016/8/24.
 */
public class CateBoyBean {
    /**
     * sucessfully : ok
     * boy : [{"_id":"1","name":"上衣","SexId":"1"},{"_id":"2","name":"裤子","SexId":"1"},{"_id":"3","name":"鞋子","SexId":"1"},{"_id":"4","name":"配饰","SexId":"1"},{"_id":"5","name":"包类","SexId":"1"},{"_id":"6","name":"内衣家居服","SexId":"1"},{"_id":"7","name":"创意生活","SexId":"1"},{"_id":"8","name":"泳衣","SexId":"1"},{"_id":"9","name":"潮童","SexId":"1"}]
     */

    private String sucessfully;
    /**
     * _id : 1
     * name : 上衣
     * SexId : 1
     */

    private List<BoyBean> boy;

    public String getSucessfully() {
        return sucessfully;
    }

    public void setSucessfully(String sucessfully) {
        this.sucessfully = sucessfully;
    }

    public List<BoyBean> getBoy() {
        return boy;
    }

    public void setBoy(List<BoyBean> boy) {
        this.boy = boy;
    }

    public static class BoyBean {
        private String _id;
        private String name;
        private String SexId;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSexId() {
            return SexId;
        }

        public void setSexId(String SexId) {

            this.SexId = SexId;
        }

        @Override
        public String toString() {
            return "BoyBean{" +
                    "_id='" + _id + '\'' +
                    ", name='" + name + '\'' +
                    ", SexId='" + SexId + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CateBoyBean{" +
                "sucessfully='" + sucessfully + '\'' +
                ", boy=" + boy +
                '}';
    }
}
