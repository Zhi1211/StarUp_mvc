package com.web.store.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.model.FormBean;
import com.web.store.repository.FormDao;
import com.web.store.service.FormService;

@Transactional
@Service
public class FormServiceImpl implements FormService {
	
	@Autowired
	FormDao formdao;

	public FormServiceImpl() {
	}

	@Override
	public void getAllForms() {
		formdao.getAllForms();
		
	}

	@Override
	public void processForm(FormBean fb) {
    formdao.processForm(fb);		
	}

}
