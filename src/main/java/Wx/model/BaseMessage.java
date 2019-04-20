/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: BaseMessage
 * Author:   Administrator
 * Date:     2018/12/13 16:11
 * Description: 基础公共类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package Wx.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈基础公共类〉
 *
 * @author cfy
 * @create 2018/12/13
 * @since 1.0.0
 */
public class BaseMessage {
    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private String createTime;
    @XStreamAlias("MsgType")
    private String msgType;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }


    public BaseMessage(Map<String, String> requestMap){
            this.toUserName=requestMap.get("FromUserName");
            this.fromUserName=requestMap.get("ToUserName");
           this.createTime= System.currentTimeMillis()/1000+"";
    }
}



