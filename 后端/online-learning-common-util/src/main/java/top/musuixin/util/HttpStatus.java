package top.musuixin.util;


/**
 * @author musuixin
 * @date 2020-03-31 20:38
 */
public class HttpStatus {
    /**
     * 请求成功
     */
    public static final int HTTP_OK = 200;
    /**
     * 创建成功
     */
    public static final int HTTP_CREATED = 201;
    /**
     * 错误的请求
     */
    public static final int HTTP_BAD_REQUEST = 400;
    /**
     * 被拒绝
     */
    public static final int HTTP_FORBIDDEN = 403;
    /**
     * 无法找到
     */
    public static final int HTTP_NOT_FOUND = 404;
    /**
     * 资源冲突
     */
    public static final int HTTP_CONFLICT = 409;
    /**
     * 服务器内部错误
     */
    public static final int HTTP_INTERNAL_ERROR = 500;
}
