/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author ASUS
 */
public class LookUp {

    public enum JSONKeys {

        ADDRESS("address"),
        ADDRESS_DETAIL("address_detail"),
        ADDRESS_POSITION("address_position"),
        AMOUNT("amount"),
        AREA("area"),
        AREA__SR_K("area__sr_k"),
        AR_SQKM("ar_sqkm"),
        AVE_AREA("ave_area"),
        BUSINESS_NAME("business_name"),
        CATEGORY("category"),
        CONTENT("content"),
        CURRENT_AREA_CODE("current_area_code"),
        DATE_TIME("date_time"),
        DENSITY_SQ("density_sq"),
        DISTBNDC_I("distbndc_i"),
        DIST_NAME("dist_name"),
        DIVISION_CODE("division_code"),
        DIVISION_ID("division_id"),
        DIVISION_NAME("division_name"),
        FIRST_DIST("first_dist"),
        FRE("fre"),
        GEO_CODE("geo_code"),
        ITEM_ID("item_id"),
        ITEM_NAME("item_name"),
        LATITUDE("latitude"),
        LOCATION("location"),
        LONGITUDE("longitude"),
        MAJOR_TYPE("major_type"),
        MINOR_TYPE("minor_type"),
        NAME("name"),
        OWNER("owner"),
        PER("per"),
        PERIMETER("perimeter"),
        PERSON_POS_ID("person_pos_id"),
        PHONE("phone"),
        POPULATION("population"),
        PRICE("price"),
        REC_ID("rec_id"),
        THE_GEOM("the_geom"),
        SALES_ID("sales_id"),
        SALESMAN_ID("salesman_id"),
        SEC("sec"),
        SHOP_ID("shop_id"),
        SHOP_NAME("shop_name"),
        SHOP_RATING("shop_rating"),
        SUBCATEGORY("subcategory"),
        SUM_AREA("sum_area"),
        STATUS("status"),
        THANA("thana"),
        THANA_ID("thana_id"),
        THANA_NAME("thana_name"),
        TOP("top"),
        UPAZILLA_ID("upazilla_id"),
        ZILLA_ID("zilla_id");
        public String name = "";

        private JSONKeys(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum MSGKeys {

        INSERTED("Informaltion added successfully."),
        UPDATES("Informaltion modified successfully."),
        PARAM_MISSING("Please give [PARAM_NAME]"),
        INSERTION_FAILED("Insertion failed!!");
        public String name = "";

        private MSGKeys(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum RespStatus {

        SUCCESS(0),
        GENERAL_EXCEPTION(100),
        INVALID_RESULTS(101),
        SQL_ERROR(102),
        DB_CONN_ERROR(103),
        NOT_LOGGED_IN(104),
        INVALID_CMD_PARAMS(105),
        INVALID_CMD(106),
        INCORRECT_VALUE_EXCP(107),
        PERMISSION_DENIED(108),
        MISSING_CMD_PARAMS(109),
        NO_DATA_FOUND_ERROR(110),
        DUPLICATE_VALUE(111);

        private int code;

        public int code() {

            return code;

        }

        RespStatus(int code) {
            this.code = code;
        }
    }
}
