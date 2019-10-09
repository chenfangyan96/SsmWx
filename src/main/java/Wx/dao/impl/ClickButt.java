/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ClickButt
 * Author:   chenfangyan
 * Date:     2018/12/28 11:29
 * Description: 点击事件 按钮
 * History:
 * <author>          <time>          <version>          <desc>
 * chenfangyan           修改时间           版本号              描述
 */
package Wx.dao.impl;

import Wx.dao.AbstractButt;


/**
 * 〈一句话功能简述〉<br> 
 * 〈点击事件 按钮〉
 *
 * @author chenfangyan
 * @create 2018/12/28
 * @since 1.0.0
 */
public class ClickButt  extends AbstractButt {
    private String type="click";
    private  String key ;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ClickButt(String name, String key) {
        super(name);
        this.key = key;
    }
}

