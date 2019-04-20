/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MusicMessage
 * Author:   Administrator
 * Date:     2018/12/18 17:30
 * Description: 音乐消息
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
 * 〈音乐消息〉
 *
 * @author cfy
 * @create 2018/12/18
 * @since 1.0.0
 */
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage {
    @XStreamAlias("Title")
    private String title;
    @XStreamAlias("Description")
    private String description;
    @XStreamAlias("MusicURL")
    private String musicURL;
    @XStreamAlias("TQMusicUrl")
    private String hQMusicUrl;
    @XStreamAlias("ThumbMediaId")
    private String thumbMediaId;

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

    public String getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(String musicURL) {
        this.musicURL = musicURL;
    }

    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public MusicMessage(Map<String, String> requestMap, String title, String description, String musicURL, String hQMusicUrl, String thumbMediaId) {
        super(requestMap);
        this.setMsgType("music");
        this.title = title;
        this.description = description;
        this.musicURL = musicURL;
        this.hQMusicUrl = hQMusicUrl;
        this.thumbMediaId = thumbMediaId;
    }
}

