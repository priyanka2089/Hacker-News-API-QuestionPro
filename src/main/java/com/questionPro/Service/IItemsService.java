package com.questionPro.Service;

import java.util.List;

import com.questionPro.model.CommentDTO;
import com.questionPro.model.StoriesDTO;

public interface IItemsService {

	public List<StoriesDTO> getTop10Story();
	
	public List<StoriesDTO> getAllRecordedItemsDetails();
	
	public List<CommentDTO> getTop10CommentsByStoryID(Integer id);
	
	
}
