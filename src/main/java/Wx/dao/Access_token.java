/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Access_token
 * Author:   chenfangyan
 * Date:     2018/12/28 14:45
 * Description: 存放token实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * chenfangyan           修改时间           版本号              描述
 */
package Wx.dao;

/**
 * 〈一句话功能简述〉<br> 
 * 〈存放token实体类〉
 *
 * @author chenfangyan
 * @create 2018/12/28
 * @since 1.0.0
 */
public class Access_token {
    private String access_token;
    private  long expiresTime;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }

    public Access_token(String access_token, String expires_in) {
        this.access_token = access_token;
        this.expiresTime = System.currentTimeMillis()+ Integer.parseInt(expires_in)*1000;
    }
    //判断access——token是否过期
    public  boolean isExpired(){
        return System.currentTimeMillis()>expiresTime;
    }
}

