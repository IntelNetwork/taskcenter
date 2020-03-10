package org.smartwork.comm.constant;
/***
 * CommonConstant概要说明：常用常量
 * @author Huanghy
 */
public interface CommonConstant {
	
	
	/*******http状态码********/
	public static final String X_ACCESS_TOKEN = "X-Access-Token";
	/** {@code 500 Server Error} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /** {@code 200 OK} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_OK_200 = 200;
    /***********/
    public static final String PREFIX_USER_ROLE = "PREFIX_USER_ROLE";
    public static final String PREFIX_USER_PERMISSION  = "PREFIX_USER_PERMISSION";
    public static final int  TOKEN_EXPIRE_TIME  = 3600; //3600秒即是一小时
    public static final String PREFIX_USER_TOKEN  = "PREFIX_USER_TOKEN";
    public static final String PREFIX_USER  = "PREFIX_USER%s";
    /****默认空值
     */
    public static final String DEFAULT_EMPTY = "";
    public static final String SEPARATOR = ",";
    public static final String FILE_SEPARATOR = ".";
    public static final String USER_NAME = "username";
    public static final String PROVILES_CODE = "spring.profiles.active";
    public static final String ACTIVE_CODE = "dev";
    /***返回文件对象***/
    public static final String CN_NAME_CODE = "cnName";
    public static final String EN_NAME_CODE = "enName";
    public static final String SUFFIX_CODE = "suffix";
    public static final String FILE_PATH_CODE = "filePath";
    /***日期年月**/
    public  final String YEAR_MONTH_FORMAT = "yyyyMM";
    public  final String ORDER_PREFIX = "yyyyMMddHHmmssSSS";


}
