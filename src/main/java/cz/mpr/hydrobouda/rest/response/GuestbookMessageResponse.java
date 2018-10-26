package cz.mpr.hydrobouda.rest.response;

import cz.mpr.hydrobouda.model.Message;

public class GuestbookMessageResponse extends BaseResponse {
	protected Message message;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
}
