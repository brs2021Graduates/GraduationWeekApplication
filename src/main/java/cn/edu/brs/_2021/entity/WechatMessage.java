package cn.edu.brs._2021.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@XmlRootElement(name = "xml")
public class WechatMessage implements Serializable {
    @XmlElement(name = "ToUserName") private String toUserName;
    @XmlElement(name = "FromUserName") private String fromUserName;
    @XmlElement(name = "CreateTime") private String createTime;
    @XmlElement(name = "MsgType") private String msgType;
    @XmlElement(name = "Content") private String content;
    @XmlElement(name = "MsgId") private String msgId;
    @XmlElement(name = "PicUrl") private String picUrl;
    @XmlElement(name = "MediaId") private String mediaId;
    @XmlElement(name = "Format") private String format;
    @XmlElement(name = "Recognition") private String recognition;
    @XmlElement(name = "ThumbMediaId") private String thumbMediaId;
    @XmlElement(name = "Title") private String title;
    @XmlElement(name = "Description") private String description;
    @XmlElement(name = "Url") private String url;
    @XmlElement(name = "Event") private String event;

    @Override
    public String toString() {
        return "WechatMessage{" +
                "toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", msgType='" + msgType + '\'' +
                ", content='" + content + '\'' +
                ", msgId='" + msgId + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", mediaId='" + mediaId + '\'' +
                ", format='" + format + '\'' +
                ", recognition='" + recognition + '\'' +
                ", thumbMediaId='" + thumbMediaId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", event='" + event + '\'' +
                '}';
    }

    public WechatMessage() {
    }

    //收消息用这个
    public WechatMessage(String toUserName,
                         String fromUserName,
                         String createTime,
                         String msgType,
                         String content,
                         String msgId,
                         String picUrl,
                         String mediaId,
                         String format,
                         String recognition,
                         String thumbMediaId,
                         String title,
                         String description,
                         String url,
                         String event) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        this.msgType = msgType;
        this.content = content;
        this.msgId = msgId;
        this.picUrl = picUrl;
        this.mediaId = mediaId;
        this.format = format;
        this.recognition = recognition;
        this.thumbMediaId = thumbMediaId;
        this.title = title;
        this.description = description;
        this.url = url;
        this.event = event;
    }

    public String getToUserName() {
        return toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public String getContent() {
        return content;
    }

    public String getMsgId() {
        return msgId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public String getFormat() {
        return format;
    }

    public String getRecognition() {
        return recognition;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getEvent() {
        return event;
    }

    @XmlTransient
    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    @XmlTransient
    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    @XmlTransient
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @XmlTransient
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @XmlTransient
    public void setContent(String content) {
        this.content = content;
    }

    @XmlTransient
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @XmlTransient
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @XmlTransient
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @XmlTransient
    public void setFormat(String format) {
        this.format = format;
    }

    @XmlTransient
    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    @XmlTransient
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    @XmlTransient
    public void setTitle(String title) {
        this.title = title;
    }

    @XmlTransient
    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public void setUrl(String url) {
        this.url = url;
    }

    @XmlTransient
    public void setEvent(String event) {
        this.event = event;
    }
}
