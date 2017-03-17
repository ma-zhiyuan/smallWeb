package me.ifma.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.ifma.po.TextMessage;
import me.ifma.utils.CheckUtil;
import me.ifma.utils.MessageUtil;
import sun.misc.MessageUtils;

@WebServlet("/WeixinServlet")
public class WeixinServlet extends HttpServlet {
	private static final long serialVersionUID = -6578303372746879171L;

    public WeixinServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		PrintWriter out = response.getWriter();
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		Map<String,String> map = MessageUtil.xmlToMap(request);
		String fromUserName = map.get("FromUserName");
		String toUserName = map.get("ToUserName");
		String msgType = map.get("MsgType");
		String content = map.get("Content");
		String message="";
		if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
			if("1".equals(content)){
				message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenu());
			}else if("2".equals(content)){
				message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.secondMenu());
			}else if("?".equals(content)||"？".equals(content)){
				message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
			}else{
				message = MessageUtil.initText(toUserName, fromUserName, "未匹配到您发送的内容："+content);
			}
		}else if(MessageUtil.MESSAGE_LOCATION.equals(msgType)){
				String respMsg = "您当前在："+map.get("Label")+"\n"+"坐标：X"+map.get("Location_X")+"，Y"+map.get("Location_Y");
				message = MessageUtil.initText(toUserName, fromUserName, respMsg);
		}else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){
			String eventType = map.get("Event");
			if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
				message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
			}
		}
		System.out.println(message);
		System.out.println(map);
		pw.print(message);
	}

}
