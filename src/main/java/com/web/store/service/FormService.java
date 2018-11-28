package com.web.store.service;

import java.util.List;

import com.web.store.model.FormBean;

public interface FormService {

	public List<FormBean> getAllForms();

	public void processForm(FormBean fb);

	FormBean getFormById(Integer form_Id);
	
	public void updateFormStatus(Integer form_Id, String status);
}
