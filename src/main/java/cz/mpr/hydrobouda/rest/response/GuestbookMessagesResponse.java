package cz.mpr.hydrobouda.rest.response;

import java.util.List;

import cz.mpr.hydrobouda.model.Message;

public class GuestbookMessagesResponse extends BaseResponse {
	protected List<Message> messages;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
