package cn.edu.brs._2021.service.wechat.utility;

import cn.edu.brs._2021.entity.WechatMessage;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringReader;
import java.io.StringWriter;

@SuppressWarnings("unused")
public abstract class WechatMessageUtil {
    private static final String USERNAME = "gh_da5d596e2ead";

    public static WechatMessage generateErrorMessage(String toUserName){
        return generateTextMessage(toUserName,
                "后端服务出现错误，请联系微信brs2021Graduates / 邮箱 brs2021Graduates@outlook.com，标注后台错误。");
    }

    public static WechatMessage generateTextMessage(String toUserName, String content){
        WechatMessage wechatMessage = generateTemplateMessage();
        wechatMessage.setToUserName(toUserName);
        wechatMessage.setMsgType("text");
        wechatMessage.setContent(content);
        return wechatMessage;
    }

    //TODO: 实现，注意xml结构是嵌套的。
    // <Image>
    //    <MediaId><![CDATA[media_id]]></MediaId>
    // </Image>
    public static WechatMessage generatePictureMessage(String toUserName, String content){
        return null;
    }

    public static String covertWechatMessageToXmlString(WechatMessage wechatMessage){
        StringWriter writer = new StringWriter();
        try {
            Marshaller marshaller = JAXBContext.newInstance(WechatMessage.class).createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.marshal(wechatMessage, writer);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    public static WechatMessage convertXmlToWechatMessage(String xmlWechatMessage){
        return JAXB.unmarshal(new StringReader(xmlWechatMessage), WechatMessage.class);
    }

    private static WechatMessage generateTemplateMessage(){
        WechatMessage wechatMessage = new WechatMessage();
        wechatMessage.setFromUserName(USERNAME);
        wechatMessage.setCreateTime(Long.toString(System.currentTimeMillis()));
        return wechatMessage;
    }
}
