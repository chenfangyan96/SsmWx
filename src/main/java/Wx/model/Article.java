package Wx.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *tuwen
 *cfy
 */
@XStreamAlias("item")
public class Article {
	//Title	是	图文消息标题
	//Description	是	图文消息描述
	//PicUrl	是	图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	//Url	是	点击图文消息跳转链接
	@XStreamAlias("Title")
	private  String title;
	@XStreamAlias("Description")
	private  String description;
	@XStreamAlias("PicUrl")
	private  String picUrl;
	@XStreamAlias("Url")
	private  String url;

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

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Article(String title, String description, String picUrl, String url) {
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
		this.url = url;
	}
}
