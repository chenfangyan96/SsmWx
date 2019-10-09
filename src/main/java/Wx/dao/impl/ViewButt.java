/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ViewButt
 * Author:   chenfangyan
 * Date:     2018/12/28 12:06
 * Description: 按钮菜单
 * History:
 * <author>          <time>          <version>          <desc>
 * chenfangyan           修改时间           版本号              描述
 */
package Wx.dao.impl;

import Wx.dao.AbstractButt;


/**
 * 〈一句话功能简述〉<br> 
 * 〈按钮菜单〉
 *
 * @author chenfangyan
 * @create 2018/12/28
 * @since 1.0.0
 */
public class ViewButt extends AbstractButt {
    private  String type = "view";
    private  String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ViewButt(String name, String url) {
        super(name);
        this.url = url;
    }
}

