/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.giochisulnostrotavolo.listone.exception;

public class SecureException extends Exception {
    
	private static final long serialVersionUID = -2324411983164748687L;
	
	private String msgDetail;
    
    public SecureException(String pMessage, String pMsgDetail) {
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
