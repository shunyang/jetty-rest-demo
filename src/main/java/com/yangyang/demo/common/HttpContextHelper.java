package com.yangyang.demo.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.yangyang.demo.utils.JsonUtils;

/**
 * 与Http请求相关的工具类
 */
public class HttpContextHelper {

    private static Logger Log = LoggerFactory.getLogger(HttpContextHelper.class);

    public static ResponseEntity<?> buildResponse(int httpCode, int code, String content) {
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("code", code);
        body.put("message", content);
        return buildResponse(httpCode, body);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static ResponseEntity<?> buildResponse(int httpCode, Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        Log.info("Response: httpCode:{}, body:{}", httpCode, JsonUtils.toJson(body));
        return new ResponseEntity(body, headers, HttpStatus.valueOf(httpCode));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static ResponseEntity<?> buildResponse(RespCode respCode, Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        Log.info("Response: httpCode:{}, body:{}", respCode, JsonUtils.toJson(body));
        return new ResponseEntity(body, headers, HttpStatus.valueOf(respCode.getHttpCode()));
    }


    @SuppressWarnings("rawtypes")
	public static ResponseEntity buildResponse(RespCode errorCode) {
        return buildResponse(errorCode.getHttpCode(), errorCode.getCode(), errorCode.getMessage());
    }

    public static void response(HttpServletResponse response, RespCode errorCode) throws IOException {
        response.setContentType("application/json");
        response.setStatus(errorCode.getHttpCode());
        response.getWriter().print(errorCode.toJson());
    }



    /**
     * 获取请求的客户端的 IP 地址。若应用服务器前端配有反向代理的 Web 服务器，
     * 需要在 Web 服务器中将客户端原始请求的 IP 地址加入到 HTTP header 中。
     * 详见 {@link #PROXY_REMOTE_IP_ADDRESS}
     */
    public static String getRemoteIp(HttpServletRequest request ) {
        for (String key : PROXY_REMOTE_IP_ADDRESS) {
            String ip = request.getHeader(key);
            if (ip != null && ip.trim().length() > 0) {
                return getRemoteIpFromForward(ip.trim());
            }
        }
        return request.getRemoteHost();
    }

    /**
     * Web 服务器反向代理中用于存放客户端原始 IP 地址的 Http header 名字，
     * 若新增其他的需要增加或者修改其中的值。
     */
    private static final String[] PROXY_REMOTE_IP_ADDRESS = { "X-Forwarded-For", "X-Real-IP" };

    /**
     * 从 HTTP Header 中截取客户端连接 IP 地址。如果经过多次反向代理，
     * 在请求头中获得的是以“,”分隔 IP 地址链，第一段为客户端 IP 地址。
     * @param xForwardIp 从 HTTP 请求头中获取转发过来的 IP 地址链
     * @return 客户端源 IP 地址
     */
    private static String getRemoteIpFromForward( String xForwardIp) {
        int commaOffset = xForwardIp.indexOf(',');
        if (commaOffset < 0) {
            return xForwardIp;
        }
        return xForwardIp.substring(0 , commaOffset);
    }
}