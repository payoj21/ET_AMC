package com.til.controller;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.til.bean.AMCNameDto;
import com.til.daoImpl.TopAMC;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class AMCController {

	
	@Autowired
	private TopAMC dao;
	//@Autowired
	//TopAMC amcDao;
	
	@Autowired
	public void init(){
		//public List<AMCNameDto> getAMCData();
		//startupDao.loadWrittenFileIds();
		//startupDao.removeWrittenFileId();
		
	/*	List<AMCNameDto> l = new ArrayList<AMCNameDto>(); 
		l = test();*/
		
	}
	
	@RequestMapping("/AMCDetails")
	@ResponseBody
	public String test(HttpServletRequest request)
			//@RequestParam (value="callback", required= false, defaultValue="AMCDetails" ) String callback)
			{
	
		//startupDao.loadStartUpComMap();
		//ComUtil.FILE_NO=0;
		List<AMCNameDto> output = new ArrayList<AMCNameDto>();
		Gson gson = new GsonBuilder().create();
		String response = null;
		
		output = dao.getAMCData();
		
		//String s  = output.toString();
		String callback = request.getParameter("callback");	
		String json = gson.toJson(output);
		response = callback + "(" + json + ")";
		//String response= callback+ json;
		
		return response;
		
	}
	
	
	
}





//HttpServletRequest
