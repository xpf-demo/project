package webSocket.entity;

import java.util.Date;

public class Message {

    //发送方
    private Long from;
    //发送方名称
    private String fromName;
    //接收方
    private Long to;
    //发送的消息
    private String text;
    //发送时间
    private Date date;
    //房间名称
    private String roomsName;

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

	public String getRoomsName() {
		return roomsName;
	}

	public void setRoomsName(String roomsName) {
		this.roomsName = roomsName;
	}
    
    

}