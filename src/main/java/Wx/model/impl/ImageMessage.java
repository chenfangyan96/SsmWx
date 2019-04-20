/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ImageMessage
 * Author:   Administrator
 * Date:     2018/12/18 16:51
 * Description:
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
 * 〈〉
 *
 * @author cfy
 * @create 2018/12/18
 * @since 1.0.0
 */
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage {
    @XStreamAlias("MediaId")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public ImageMessage(Map<String, String> requestMap, String mediaId) {
        super(requestMap);
        this.setMsgType("image");
        this.mediaId=mediaId;

    }
}

