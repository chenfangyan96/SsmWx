/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TextMessage
 * Author:   Administrator
 * Date:     2018/12/13 16:36
 * Description: 文本信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package Wx.model.impl;

import Wx.model.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈文本信息〉
 *
 * @author Administrator
 * @create 2018/12/13
 * @since 1.0.0
 */
@XStreamAlias("xml")
public class TextMessage extends BaseMessage {
    @XStreamAlias("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public  TextMessage(Map<String, String> requestMap , String content){
        super(requestMap);
        this.setMsgType("text");
        this.setContent(content);
    }

    @Override
    public String toString() {
        return  "TextMessage[content="+this.getContent()+",CreateTime"+this.getCreateTime()+",ToUserName"+this.getToUserName()+"]";
    }
}

