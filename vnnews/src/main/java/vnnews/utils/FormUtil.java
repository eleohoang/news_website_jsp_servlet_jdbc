package vnnews.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;


public class FormUtil {
	//map params to model type T; key - value (value is maybe not single
	@SuppressWarnings("unchecked")
	public static <T> T toModel(Class<T> tClass, HttpServletRequest request) {
		T object = null;
		try {
			object = tClass.newInstance();
			BeanUtils.populate(object, request.getParameterMap());
			
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.println(e.getMessage());
		} 
		return object;
	}

}
