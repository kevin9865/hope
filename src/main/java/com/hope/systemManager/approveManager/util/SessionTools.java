package com.hope.systemManager.approveManager.util;

import javax.faces.context.FacesContext;

public class SessionTools {
	
	public static void setContext(String key,Object value){
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
	}
	
	public static Object getContext(String key){
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}
}
