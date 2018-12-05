package com.zhk.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Desc:
 * @Author: zhanhk
 * @Date: Created in 下午2:33 18/10/19
 * @copyright Navi WeCloud
 */
public class JavaPostJson2 {
    final static String url = "http://wecloudapi.autoai.com/push/mqtt/pushMsg/api/send";

    public static void main(String[] args) {

        for (int i = 1; i <= 1; i++) {
            //String params = "{\"apiKey\":\"774a05a7780448c6a2117d9a66ac2ad7\",\"actionType\":\"1\",\"broadcastType\":\"1\",\"message\":\"MQTT推送-" + i + ":" + DateUtil.format(DateUtil.getDate())+ "\",\"messageType\":2,\"params\":\"{}\",\"title\":\"MQTT推送测试-" + i + ":" + DateUtil.format(DateUtil.getDate()) + "\"}";
            String params = "{\"apiKey\":\"774a05a7780448c6a2117d9a66ac2ad7\",\"actionType\":\"1\",\"broadcastType\":\"2\",\"deviceIds\":\"15B0E5BD-5BFE-A796-379F-D936F660EAEA\",\"message\":\"MQTT推送\",\"messageType\":2,\"params\":\"{}\",\"title\":\"MQTT推送测试-" + i + ":" + DateUtil.format(DateUtil.getDate()) +"\"}";
            String res = post(url, params);
            System.out.println(res);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送HttpPost请求
     *
     * @param strURL 服务地址
     * @param params json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
     * @return 成功:返回json字符串<br/>
     */
    public static String post(String strURL, String params) {
        System.out.println(strURL);
        System.out.println(params);
        BufferedReader reader = null;
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            // connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();

            //如果一定要使用如下方式接收响应数据， 则响应必须为: response.getWriter().print(StringUtils.join("{\"errCode\":\"1\",\"errMsg\":\"", message, "\"}")); 来返回
//            int length = (int) connection.getContentLength();// 获取长度
//            if (length != -1) {
//                byte[] data = new byte[length];
//                byte[] temp = new byte[512];
//                int readLen = 0;
//                int destPos = 0;
//                while ((readLen = is.read(temp)) > 0) {
//                    System.arraycopy(temp, 0, data, destPos, readLen);
//                    destPos += readLen;
//                }
//                String result = new String(data, "UTF-8"); // utf-8编码
//                System.out.println(result);
//                return result;
//            }

            return res;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error"; // 自定义错误信息
    }
}
