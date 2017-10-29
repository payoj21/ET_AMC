package com.til.controller;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;





import com.til.bean.SchemeDetailDto;
import com.til.daoImpl.AMCinfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class SchemeController {

	@Autowired
	private AMCinfo dao;
	//@Autowired
	//TopAMC amcDao;
	
	//@Autowired
	public void init(){
		//public List<AMCNameDto> getAMCData();
		//startupDao.loadWrittenFileIds();
		//startupDao.removeWrittenFileId();
	}
	
	@RequestMapping("/SchemeDetails")
	@ResponseBody
	public String test(HttpServletRequest request){
		String response = null;
		if(request.getParameter("amcId")!= null && !request.getParameter("amcId").isEmpty())
		{int AMCid = Integer.parseInt(request.getParameter("amcId"));
		//startupDao.loadStartUpComMap();
		//ComUtil.FILE_NO=0;
		List<SchemeDetailDto> output = new ArrayList<SchemeDetailDto>();
		Gson gson = new GsonBuilder().create();
		String callback = request.getParameter("callback");
		output = dao.getAMC(AMCid);
		System.out.println(output);
		String json = gson.toJson(output);
		response = callback + "(" + json + ")";
		}
		return response;
			
		
		
		
	}
	
}
