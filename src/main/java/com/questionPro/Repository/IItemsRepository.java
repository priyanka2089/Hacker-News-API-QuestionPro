package com.questionPro.Repository;

import java.io.IOException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.questionPro.model.CommentDTO;
import com.questionPro.model.StoriesDTO;

public interface IItemsRepository{
	
	public  List<StoriesDTO> getTop10Stories() throws Exception;
	
	public List<StoriesDTO> getStoryDetails(List<Integer> ids) throws Exception;
	
	public List<StoriesDTO> getAllRecordedItemsDetails() throws Exception;
	
	public List<CommentDTO> getTop10CommentsByStoryID(Integer id) throws Exception;
	
	
	

}
