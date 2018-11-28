package com.web.store.repository;

import java.util.List;

import com.web.store.model.FormBean;

public interface FormDao {
	List<FormBean> getAllForms();

	void processForm(FormBean fb);
	
	FormBean getFormById(Integer form_id);
	
	void updateFormStatus(Integer form_id, String status);

}
