package top.musuixin.util;

import lombok.Data;


/**
 * HTTP结果封装
 *
 * @author Louis
 * @date Jan 12, 2019
 */
@Data
public class HttpResult {
    private int code;
    private String msg;
    private Object data;

    /**
     * 请求成功 响应
     * @param data
     * @return
     */
    public static HttpResult HTTP_OK(Object data) {
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(HttpStatus.HTTP_OK);
        httpResult.setMsg("请求成功");
        httpResult.setData(data);
        return httpResult;
    }
    /**
     * 创建成功 响应
     * @param data
     * @return
     */
    public static HttpResult HTTP_CREATED(Object data) {
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(HttpStatus.HTTP_CREATED);
        httpResult.setMsg("创建成功");
        httpResult.setData(data);
        return httpResult;
    }
    /**
     * 错误的请求 响应
     * @param data
     * @return
     */
    public static HttpResult HTTP_BAD_REQUEST(Object data) {
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(HttpStatus.HTTP_BAD_REQUEST);
        httpResult.setMsg("错误的请求");
        httpResult.setData(data);
        return httpResult;
    }
    /**
     * 被拒绝 响应
     * @param data
     * @return
     */
    public static HttpResult HTTP_FORBIDDEN(Object data) {
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(HttpStatus.HTTP_FORBIDDEN);
        httpResult.setMsg("被拒绝");
        httpResult.setData(data);
        return httpResult;
    }
    /**
     * 无法找到 响应
     * @param data
     * @return
     */
    public static HttpResult HTTP_NOT_FOUND(Object data) {
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(HttpStatus.HTTP_NOT_FOUND);
        httpResult.setMsg("无法找到");
        httpResult.setData(data);
        return httpResult;
    }
    /**
     *  第三方账户没有绑定 专有错误
     * @param data
     * @return
     */
    public static HttpResult HTTP_CONFLICT(Object data) {
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(HttpStatus.HTTP_CONFLICT);
        httpResult.setMsg("第三方账户出错");
        httpResult.setData(data);
        return httpResult;
    }
    /**
     * 服务器内部错误 响应
     * @param data
     * @return
     */
    public static HttpResult HTTP_INTERNAL_ERROR(Object data) {
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
        httpResult.setMsg("服务器内部错误");
        httpResult.setData(data);
        return httpResult;
    }
}
