package Wx.Controller;

import Wx.Service.ArticleService;
import Wx.Service.UserService;
import Wx.Service.WxService;
import Wx.dao.webAccessToken;
import Wx.model.User;
import Wx.util.CacheUtil;
import Wx.util.Decript;
import Wx.util.WxUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.*;

@Controller
@RequestMapping("/test")
public class test {
    private static Logger logger =Logger.getLogger(test.class);
    @Autowired
    private ArticleService articleService;
    @Autowired
    private  UserService userService;
    @Autowired
    private Producer captchaProducer = null;
    @Autowired
    public  WxService wxService;
    //获取用户信息
    private  static  String userinfo="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    private static final String KAPTCHA_SESSION_KEY = com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
    @RequestMapping("/login")
    public  String  login(HttpServletRequest request,Model model){
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String kaptcha = request.getParameter("kaptcha");
        HttpSession session = request.getSession();
        String  attribute = (String)session.getAttribute(KAPTCHA_SESSION_KEY);
        logger.info(attribute);
        if (!attribute.equals(kaptcha)){
            model.addAttribute("msg","您输入的验证码有误，请重新输入");
            return "/login1";
        }else {
            return "/main";
        }

    }
    @RequestMapping("/tomy")
    public String person(String code,Model model){
        logger.info("获取到用户的code码"+code);
        String web_token=null;
        String openid =null;
        JSONObject web_access_token = wxService.get_WEB_access_Token(code);
        openid = web_access_token.getString("openid");
        web_token=web_access_token.getString("access_token");
        JSONObject userinfo = wxService.userinfo(web_token, openid);
        logger.info("获取到的用户信息为"+userinfo);
        Set<Map.Entry<String,Object>> entries= userinfo.entrySet();
        for (Map.Entry<String,Object> entry:entries){
            model.addAttribute(entry.getKey(),entry.getValue());
        }
        return "/person";
    }

    @RequestMapping("login1")
    public String login1(){
        return "/main";
    }
    @RequestMapping(value = "/captcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("获取验证码");
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        logger.info("获取到验证码为"+capText);
        //向客户端写出
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        }finally {
            out.close();
        }
    }
    @RequestMapping("/sayhello")
    public String sayhello(Model model){
      //  List<Article>  articleList= articleService.findUserList();
       // model.addAttribute("list",articleList);
        return "/login";

    }
    @RequestMapping("/workbench")
    public  String workbench( HttpServletRequest httpServletRequest,Model model){
        return "/workbench";
    }
    @RequestMapping("/info")
    public  String info(){
        return "/main/info";
    }
    @RequestMapping("/toList")
    public String list(){
        return  "/sysUser/list";
    }
    @RequestMapping("/toAdd")
    public String toadd(){
        return  "/sysUser/add";
    }
    @RequestMapping("/initConfig")
    public String toinitConfig(){
        return  "/reply/config";
    }
    @RequestMapping("/luck")
    public String luck(Model model,HttpServletRequest  request){
        String token = wxService.getToken();
        String jspi = wxService.getJspi(token);
        logger.info("jspi====="+jspi);
        String  noncevalue= RandomStringUtils.randomAlphanumeric(10).toString();
        String timestampvalue = (new Date().getTime() / 1000)+"";
        logger.info("nonce"+noncevalue);
        logger.info("timestamp"+timestampvalue);
        String requestURI = request.getRequestURL().toString();
        logger.info("requersturl"+requestURI);
        String signvlue = WxUtil.signature(jspi,timestampvalue,noncevalue,requestURI);
        logger.info("signvalue"+signvlue);
        model.addAttribute("nonce",noncevalue);
        model.addAttribute("timestampvalue",timestampvalue);
        model.addAttribute("signvalue",signvlue);
        return  "/gift";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONObject delete(Long id){
        JSONObject object = new JSONObject();
        int result =userService.delete(id);
        logger.info("------------------------------删除   成功----------------------");
        object.put("res",result);
        return object;

    }
    @RequestMapping("/toPsw")
    public  String psw(){
        return "/main/psw";
    }
    @RequestMapping("/updatePsw")
    public  String updatePsw(HttpServletRequest request, HttpServletResponse response,Model model){
        logger.info("-------updatePsw-------------");
        HttpSession session = request.getSession();
        String password_old=request.getParameter("password_old");
        String password = request.getParameter("User_password");
        // 从session　中取出来用户数据
        User aUser =(User)session.getAttribute("user");
        String msg = null;
        // 旧密码比对
        if(aUser.getPassword().equals(password_old)){
            aUser.setPassword(password);

            // 执行更新操作
           userService.update(aUser);
            msg = "修改成功！";
        }else {
            // 旧密码错误，不能更新
            msg = "旧密码错误！";
        }
        model.addAttribute("msg",msg);
        return "/main/psw";

    }
    @RequestMapping("/list")
    public String list(HttpServletRequest request,Model model){
        String username = request.getParameter("username");
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "/sysUser/list";

    }
    @RequestMapping("/redis")
    public String redisTest() {
        try {
            String token = wxService.getToken();
            System.out.println(token);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return "/hello";
    }
}
