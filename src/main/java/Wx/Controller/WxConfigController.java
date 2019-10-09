package Wx.Controller;


import Wx.Service.WxService;
import Wx.util.Decript;
import Wx.util.WxUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
public class WxConfigController   {
    private final String token="helloworld";
    private static Logger logger =Logger.getLogger(WxConfigController.class);
    @Autowired
    private  WxService wxService;


    @RequestMapping(value = "/wechat",method = RequestMethod.GET)
    @ResponseBody
    public String  doGet(String signature,String timestamp,String nonce,String echostr) {
        logger.info("进入微信签名校验");
        String sortString = WxUtil.sort(token, timestamp, nonce);
        //加密
        String mytoken = Decript.SHA1(sortString);
        if (mytoken.equals(signature)) {
            System.out.println("签名校验通过。");
            logger.info("签名校验通过");
            return echostr;
            //resp.getWriter().println(echostr); //如果检验成功输出echostr，微信服务器接收到此输出，才会确认检验完成。
        } else {

            logger.info("------签名校验失败----------");
            return  null;
        }

    }
    @RequestMapping(value = "/wechat",method = RequestMethod.POST)
    @ResponseBody
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将请求的xml转换成map
        Map<String,String> map=WxUtil.parseRequest(req.getInputStream());
        System.out.println(map.toString());
        logger.info("-----"+map.toString()+"------");
        //根据客户发送的消息进行响应
        String respxml= wxService.getResponse(map);
        System.out.println(respxml);
        PrintWriter out =resp.getWriter();
        out.print(respxml);
        out.flush();
        out.close();
    }
}
