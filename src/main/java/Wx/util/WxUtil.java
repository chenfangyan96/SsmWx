package Wx.util;

import Wx.dao.Access_token;
import Wx.model.BaseMessage;
import Wx.model.impl.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Wx.util.netUtil.net;

public class WxUtil {


    private static final String APPID = "wx6996b3f0921cbe08";
    private static final String APPSECRET = "3b8d6b3c8e34df6d519a4452c338d016";
    private static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static Access_token tk;
    private static final String APPKEY = "1fec136dbd19f44743803f89bd55ca62";


    /**
     * 排序方法
     * @param token  token
     * @param timestamp时间戳
     * @param nonce
     * @return 排序后的字符串
     */
    public  static  String sort(String token, String timestamp, String nonce){

            String[] strArray = { token, timestamp, nonce };
            Arrays.sort(strArray);

            StringBuilder sbuilder = new StringBuilder();
            for (String str : strArray) {
                sbuilder.append(str);
            }

            return sbuilder.toString();
        }
    /**
     * 获取access-token的url
     * @param url  地址
     */
    public static String getTokenUrl(String url){
        try {
            URL urlObj = new URL(url);
            URLConnection connection =urlObj.openConnection();
            InputStream inputStream = connection.getInputStream();
            byte [] b = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len=inputStream.read(b))!=-1){
                sb.append(new String(b,0,len));

            }

            return  sb.toString();


        }catch (Exception e){
            e.printStackTrace();

        }
        return null;


    }
    // xml转map集合
    public static Map<String, String> parseRequest(InputStream is) {
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        List<Element> elements = root.elements();
        for (Element e : elements) {
            map.put(e.getName(), e.getStringValue());
        }
        return map;
    }
    /**
     * 处理@XstreamAlias()注释的返回消息
     */
    public static String TOxml(BaseMessage msg) {

        XStream stream = new XStream();
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(MusicMessage.class);
        stream.processAnnotations(VideoMessage.class);
        stream.processAnnotations(NewsMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        stream.processAnnotations(ImageMessage.class);
        String xml;
        xml = stream.toXML(msg);
        return xml;

    }
    /*
     * 图灵机器人
     *
     *
     *
     *
     */
    public  static String chat(String msg) {
        String result = null;
        String url = "http://op.juhe.cn/robot/index";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key", APPKEY);//您申请到的本接口专用的APPKEY
        params.put("info", msg);//要发送给机器人的内容，不要超过30个字符
        params.put("dtype", "");//返回的数据的格式，json或xml，默认为json
        params.put("loc", "");//地点，如北京中关村
        params.put("lon", "");//经度，东经116.234632（小数点后保留6位），需要写为116234632
        params.put("lat", "");//纬度，北纬40.234632（小数点后保留6位），需要写为40234632
        params.put("userid", "");//1~32位，此userid针对您自己的每一个用户，用于上下文的关联
        try {
            result = net(url, params, "GET");
            //解析json
            JSONObject jsonObject = JSONObject.parseObject(result);
            //取出error_code
            int code = jsonObject.getInteger("error_code");
            if (code != 0) {
                return null;
            }
            //取出返回的消息的内容
            String resp = jsonObject.getJSONObject("result").getString("text");
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /*
     * 有道翻译
     *
     *
     *
     *
     */
    public  static String transfer(String msg) {
        String result = null;
        String url = "http://fanyi.youdao.com/openapi.do";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("keyfrom", "81face");//您申请到的本接口专用的APPKEY
        params.put("key","1651620054");//要发送给机器人的内容，不要超过30个字符
        params.put("type", "data");//返回的数据的格式，json或xml，默认为json
        params.put("doctype", "json");//地点，如北京中关村
        params.put("version", "1.1");//经度，东经116.234632（小数点后保留6位），需要写为116234632
        params.put("only", "translate");//纬度，北纬40.234632（小数点后保留6位），需要写为40234632
        params.put("q",msg);//1~32位，此userid针对您自己的每一个用户，用于上下文的关联
        try{
            result = netUtil.net(url, params, "GET");
            //解析json
            JSONObject jsonObject = JSONObject.parseObject(result);
            System.out.println(jsonObject.toString());
            //取出error_code
            int code = jsonObject.getInteger("errorCode");
            if (code != 0) {
                return null;
            }
            //取出返回的消息的内容
            String query = jsonObject.get("query").toString().replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2");
            String resp = jsonObject.get("translation").toString().replace("[", "").replace("]", "").replaceAll("\"(\\w+)\"(\\s*:\\s*)", "$1$2");
            String resul="原文："+query+"\n"+"译文"+resp;
            return resul;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //1.微信精选列表
    public static JSONObject getWxjx()
    {
        String result =null;
        String url ="http://v.juhe.cn/weixin/query";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("pno","1");//当前页数，默认1
        params.put("ps","20");//每页返回条数，最大100，默认20
        params.put("key","0602ba3f60a44f8ed05bcb4649c6b23b");//应用APPKEY(应用详细页查询)
        params.put("dtype","");//返回数据的格式,xml或json，默认json
        try {
            result =netUtil.net(url, params, "GET");
            JSONObject object = JSONObject.parseObject(result);

            if(object.getInteger("error_code")==0){
                String list = object.getString("result");
                JSONObject object1 = JSONObject.parseObject(list);
                JSONArray jsonArray = object1.getJSONArray("list");
                JSONObject o = (JSONObject)jsonArray.get(((int)(Math.random()*20    )));
                //获取微信精选的url
                //String urll = o.getString("url");
                //获取微信精选的标题
                //String title = o.getString("title");
                //System.out.println(urll);
                //System.out.println(title);
                return  o;

            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
                return null;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    public static void main(String[] args) {
        System.out.println(transfer("我爱你"));
        System.out.println(transfer("i love you"));
    }





}
