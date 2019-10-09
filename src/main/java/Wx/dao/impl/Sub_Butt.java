/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Sub_Butt
 * Author:   chenfangyan
 * Date:     2018/12/28 14:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * chenfangyan           修改时间           版本号              描述
 */
package Wx.dao.impl;

import Wx.dao.AbstractButt;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author chenfangyan
 * @create 2018/12/28
 * @since 1.0.0
 */
public class Sub_Butt extends AbstractButt {

        public List<AbstractButt> sub_button = new ArrayList<>();

    public List<AbstractButt> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<AbstractButt> sub_button) {
        this.sub_button = sub_button;
    }

    public Sub_Butt(String name) {
        super(name);

    }
}

