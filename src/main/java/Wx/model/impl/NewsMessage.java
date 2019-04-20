/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: NewsMessage
 * Author:   Administrator
 * Date:     2018/12/18 18:00
 * Description: 图文消息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package Wx.model.impl;

import Wx.model.Article;
import Wx.model.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈图文消息〉
 *
 * @author cfy
 * @create 2018/12/18
 * @since 1.0.0
 */
@XStreamAlias("xml")
public class NewsMessage  extends BaseMessage {
    @XStreamAlias("ArticleCount")
    private String articleCount;
    @XStreamAlias("Articles")
    private List<Article> articles = new ArrayList<Article>();

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public NewsMessage(Map<String, String> requestMap, String articleCount, List<Article> articles) {
        super(requestMap);
        this.setMsgType("news");
        this.articleCount = articleCount;
        this.articles = articles;
    }
}

