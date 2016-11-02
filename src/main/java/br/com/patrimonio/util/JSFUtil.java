package br.com.patrimonio.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {
    //Mensagem de Sucesso

    public static void adicionaMensagemSucesso(String mensagem) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);// a segunda mensagem � a mensagem detalhada
        FacesContext contexto = FacesContext.getCurrentInstance(); //FacesContext :aponta a mensagem para aplicacao 
        contexto.addMessage(null, msg);
    }

    public static void adicionaMensagemErro(String mensagem) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);// a segunda mensagem � a mensagem detalhada
        FacesContext contexto = FacesContext.getCurrentInstance(); //FacesContext :aponta a mensagem para aplicacao 
        contexto.addMessage(null, msg);

    }
}
