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
import org.springframework.util.StringUtils;

import java.util.*;

import static Wx.util.WxUtil.*;
public interface WxService {
    //获取基本的access_token
    public  String getToken();
        //获取基本的jspi
    //获取网页授权的access——token
    public JSONObject get_WEB_access_Token(String code);
    //获取用户信息
    public  JSONObject userinfo(String accesstoken,String openid);
    /*
     *处理回复消息
     *
     */
    public  String getResponse(Map<String, String> requestMap);
    /*处理文本消息
        return:返回基本消息
     */
    public  BaseMessage dealTextMessage(Map<String,String> requestMap);
    /*
        处理图片消息
     */
    public  BaseMessage delImageMessage(Map<String, String> requestMap);
    /*
     *处理事件消息
     *
     */
    public  BaseMessage dealEventMessage(Map<String, String> requestMap);
    /*
     *处理点击消息
     *
     */
    public  BaseMessage dealClick(Map<String, String> requestMap);
    public  String getJspi(String access_token1);
}