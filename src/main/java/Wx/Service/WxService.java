package Wx.Service;

import Wx.Controller.test;
import Wx.dao.Access_token;
import Wx.model.Article;
import Wx.model.BaseMessage;
import Wx.model.impl.NewsMessage;
import Wx.model.impl.TextMessage;
import Wx.util.CacheUtil;
import Wx.util.WxUtil;
import Wx.util.netUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

import static Wx.util.WxUtil.*;
@Service
public class WxService {
    //wx  配置  appID   appsecret
    private static final String APPID = "wx6996b3f0921cbe08";
    private static final String APPSECRET = "3b8d6b3c8e34df6d519a4452c338d016";
    //获取token
    private static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //获取网页授权的access——token
    private static final String GET_WEB_TOKEN_URL1 = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=wx6996b3f0921cbe08&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
           // "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx6996b3f0921cbe08&secret=3b8d6b3c8e34df6d519a4452c338d016&code=CODE&grant_type=authorization_code";
    //获取用户信息
    private  static final  String GET_WEB_USER_INFO="https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    //设置APPID/AK/SK
    private static final String APP_ID = "15773906";
    private static final String API_KEY = "Kde4g0e65O22Gws1rsLBBPGV";
    private static final String SECRET_KEY = "i4Wvq0l6lIOVsfIU0AgnoiG4ZbHPVumC";



    private static Logger logger =Logger.getLogger(WxService.class);


    //获取基本的access_token
    private static void getToken() {
        String url = GET_TOKEN_URL.replace("APPID", APPID).replace("SECRET", APPSECRET);
        String tokenUrl = WxUtil.getTokenUrl(url);
        //System.out.println(tokenUrl);
        JSONObject objJson = JSONObject.parseObject(tokenUrl);
        String access_token = objJson.getString("access_token");
        String expires_in = objJson.getString("expires_in");
        CacheUtil.setString("access_token",access_token,Long.valueOf(expires_in));
       // tk = new Access_token(access_token, expires_in);
        //System.out.println(tokenUrl);
        //  System.out.println(access_token);
        //System.out.println(expires_in);
    }

    //获取网页授权的access——token
    public static JSONObject get_WEB_access_Token(String code) {
        String url = GET_WEB_TOKEN_URL1.replace("APPID", APPID).replace("APPSECRET", APPSECRET).replace("CODE",code);
        String tokenUrl = WxUtil.getTokenUrl(url);
        JSONObject objJson = JSONObject.parseObject(tokenUrl);
        logger.info("获取到的网页授权的accesstoken"+objJson);
        String web_token = objJson.getString("access_token");
        String expires_in = objJson.getString("expires_in");
        String openid = objJson.getString("openid");
        CacheUtil.setString("web_token"+openid,web_token,expires_in);

      return objJson;
    }
    //获取用户信息
    public static JSONObject userinfo(String accesstoken,String openid) {
        String url = GET_WEB_USER_INFO.replace("ACCESS_TOKEN", accesstoken).replace("OPENID",openid);
        String result = WxUtil.getTokenUrl(url);
        logger.info("用户到的用户信息"+result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        return  jsonObject;
    }




    /*

     *处理回复消息
     *
     */
    public static String getResponse(Map<String, String> requestMap) {
        BaseMessage msg = null;
        String msgType = requestMap.get("MsgType");
        System.out.println(msgType);
        switch (msgType) {
            case "text":
                msg = dealTextMessage(requestMap);
                break;

            case "view":
                msg = dealTextMessage(requestMap);
                break;

            case "event":
                msg = dealEventMessage(requestMap);
                break;

            case "image":
                msg = delImageMessage(requestMap);
                break;


            case "location":
                break;
            default:
                break;


        }
        if (msg != null) {
            return TOxml(msg);
        } else {
            return null;
        }
    }

    /*处理文本消息
        return:返回基本消息
     */
    public static BaseMessage dealTextMessage(Map<String,String> requestMap) {
        String msg = requestMap.get("Content");
       // String msg="@helloworld";
        String resp =null;
        if(msg.trim().startsWith("@")){
            resp =transfer(msg.replace("@",""));
        }else{
        resp = chat(msg);}
        TextMessage tx = new TextMessage(requestMap, resp);
        return tx;
    }
    /*
        处理图片消息
     */
    public static BaseMessage delImageMessage(Map<String, String> requestMap) {

        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        String image = requestMap.get("PicUrl");
        org.json.JSONObject res = client.generalUrl(image,new HashMap<String, String>());
        String json =res.toString();
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray words_result = jsonObject.getJSONArray("words_result");
        Iterator it =words_result.iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()){
            JSONObject  next= (JSONObject) it.next();
            sb.append(next.getString("words"));

        }
        return new TextMessage(requestMap,sb.toString());

    }
    /*

     *处理事件消息
     *
     */

    public static BaseMessage dealEventMessage(Map<String, String> requestMap) {
        String event = requestMap.get("Event");
        switch (event) {
            case "subscribe":
                return new TextMessage(requestMap, "欢迎关注便民服务公众号！！！\n转盘抽奖请点击菜单栏<幸运大转盘>\n查看微信精选文章请点击菜单按钮<微信精选>");

            case "CLICK":
                return dealClick(requestMap);


            default:
                break;
        }
        return null;
    }
    /*

     *处理点击消息
     *
     */

    public static BaseMessage dealClick(Map<String, String> requestMap) {
        String EventKey = requestMap.get("EventKey");
        switch ("1") {
            //微信精选
            case "1":
                JSONObject wxjx = WxUtil.getWxjx();
                System.out.println(wxjx);
                List<Article> Art = new ArrayList<Article>();
                Art.add(new Article(wxjx.getString("title"),wxjx.getString("title"),"http://5b0988e595225.cdn.sohucs.com/images/20171230/fcd54cc3e5b04a318560cf822b25212e.jpeg",wxjx.getString("url")));
                NewsMessage ne=new NewsMessage(requestMap,"1",Art);
                return ne;
            case "OCR":
                return delImageMessage(requestMap);
        }
        return null;

    }

    public static void main(String[] args) {
        System.out.println(dealClick(new HashMap<String, String>()));
    }

}