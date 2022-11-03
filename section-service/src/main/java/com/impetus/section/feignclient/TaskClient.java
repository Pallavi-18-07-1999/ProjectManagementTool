package com.impetus.section.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.impetus.section.model.ResponseModel;

@FeignClient(name="task-service")
public interface TaskClient {
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseModel getTasks(@RequestParam(name="type")  String type,@RequestParam(name="data") String data);
			
}
