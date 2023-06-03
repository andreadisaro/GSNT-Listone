/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.giochisulnostrotavolo.listone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PortalException extends RuntimeException {
    
	private static final long serialVersionUID = -1870600642964861773L;
	
	private String msgDetail;
    
    public PortalException(String pMessage) {
        super(pMessage);
    }
    public PortalException(String pMessage, String pMsgDetail) {
        super(pMessage);
        this.setMsgDetail((pMsgDetail != null)? pMsgDetail : pMessage);
    }

    public String getMsgDetail() {
        return msgDetail;
    }

    public void setMsgDetail(String msgDetail) {
        this.msgDetail = msgDetail;
    }
}
