package cz.mpr.hydrobouda.rest.response;

public class BaseResponse {
	protected int httpStatusCode;

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
}
