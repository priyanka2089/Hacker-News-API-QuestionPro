package com.questionPro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.questionPro.Service.IItemsService;
import com.questionPro.model.CommentDTO;
import com.questionPro.model.StoriesDTO;

@RestController
@RequestMapping("/items")
public class ItemsController {
	
	@Autowired
	IItemsService iItemsService;
	
	
	// returns top 10 stories
	@RequestMapping(value="/top-stories",method=RequestMethod.GET)
	public List<StoriesDTO> getTopStories() {
		
		List<StoriesDTO> list = iItemsService.getTop10Story();
		return list;
	}
	
	// returns past stories which 
	@RequestMapping(value="/past-stories",method=RequestMethod.GET)
	public List<StoriesDTO> getPastStories() {
		
		List<StoriesDTO> list = iItemsService.getAllRecordedItemsDetails();
		
		return list;
	}
	
	// returns max comments 
	@RequestMapping(value="/comments",method=RequestMethod.GET)
	public List<CommentDTO> getTop10CommentsByStoryID(@RequestParam(required = true) Integer id) {
		return iItemsService.getTop10CommentsByStoryID(id);
		
	}
	

}
