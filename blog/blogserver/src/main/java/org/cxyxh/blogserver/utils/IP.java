package org.cxyxh.blogserver.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.cxyxh.blogserver.controller.ArticleController;
import org.cxyxh.blogserver.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/11 19:21
 * @describetion :
 */
public class IP {

    private final static Logger logger = LoggerFactory.getLogger(IP.class);

    final static String AK = "bklvzcmMbWC23elPSWi4lpO9NDEQWDK7";
    final static String URL_HEAD = "http://api.map.baidu.com/location/ip?ak=" + AK + "&ip=";
    final static String URL_TAIL = "&coor=bd09ll";

//    112.49.100.49
    public static void main(String[] args) {
        sendGet("112.49.100.49");
    }

    /**
     * 使用百度API ip定位
     *
     * @param ip
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Address sendGet(String ip) {
        if (StringUtils.equals(ip, "0:0:0:0:0:0:0:1")||StringUtils.equals(ip, "127.0.0.1")) {
            Address address = new Address();
            address.setProvince("福建省");
            address.setCity("福州市");
            return address;
        }
        String url = URL_HEAD + ip + URL_TAIL;
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            // 设置传递方式
            connection.setRequestMethod("GET");
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.warn("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        //unicode转中文
        result = decodeUnicode(result);
        //JSON提取想要的数据
        Address address = getAddressByApiResult(result, ip);
        return address;
    }

    /**
     * 提取JSON中的数据
     *
     * @param result JSON
     * @param ip     ip
     * @return
     */
    public static Address getAddressByApiResult(String result, String ip) {
        JSONObject obj1 = JSON.parseObject(result);
        Integer status = obj1.getInteger("status");
        if (status == 0) {
            String content = obj1.getString("content");
            JSONObject obj2 = JSON.parseObject(content);
            String addressDetail = obj2.getString("address_detail");
            JSONObject obj3 = JSON.parseObject(addressDetail);
            String province = obj3.getString("province");
            String city = obj3.getString("city");
            Address address = new Address();
            address.setProvince(province);
            address.setCity(city);
        } else {
            logger.info("该IP[{}]无法定位", ip);
        }
        return null;
    }


    /**
     * http 请求数据返回 json 中中文字符为 unicode 编码转汉字转码
     *
     * @param result
     * @return
     */
    public static String decodeUnicode(String result) {
        char aChar;
        int len = result.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = result.charAt(x++);
            if (aChar == '\\') {
                aChar = result.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = result.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
}
