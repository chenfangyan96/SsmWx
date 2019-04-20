/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: VideoMessage
 * Author:   Administrator
 * Date:     2018/12/18 17:02
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
 * 〈视频〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2018/12/18
 * @since 1.0.0
 */
@XStreamAlias("xml")
public class VideoMessage extends BaseMessage {
    @XStreamAlias("MediaId")
    private String mediaId;
    @XStreamAlias("Title")
    private String title;
    @XStreamAlias("Description")
    private String description;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VideoMessage(Map<String, String> requestMap, String title, String mediaId, String description) {
        super(requestMap);
        this.setMsgType("video");
        this.description=description;
        this.title=title;
        this.mediaId=mediaId;



    }
}

