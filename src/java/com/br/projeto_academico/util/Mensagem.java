package com.br.projeto_academico.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Renato Borges Cardoso
 * @version 1.0 13/10/2015 16:02
 */
public class Mensagem {
    
    private static Mensagem instance;
    public static Mensagem getInstance(){
        if(instance == null){
            instance = new Mensagem();
        }
        return instance;
    }
    
    public void erro(String texto){                        
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,texto,null );
        FacesContext.getCurrentInstance().addMessage(null, message); 
    }      
    
    public void advertencia(String texto){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,texto,null);
        FacesContext.getCurrentInstance().addMessage(null, message); 
    }
    
    public void warning(String texto){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, texto, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
   
}
