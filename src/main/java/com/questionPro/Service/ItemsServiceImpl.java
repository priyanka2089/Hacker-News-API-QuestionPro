package com.questionPro.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.questionPro.Repository.IItemsRepository;
import com.questionPro.model.CommentDTO;
import com.questionPro.model.StoriesDTO;

@Service
public class ItemsServiceImpl implements IItemsService {

	@Autowired
	IItemsRepository iItemsRepository;

	@Override
	@Caching(cacheable = { @Cacheable(value = "top10", key = "#root.methodName") })
	public List<StoriesDTO> getTop10Story() {
		System.out.println("################  CACHININHHHHHGGGG #########");
		List<StoriesDTO> top10StoriesDetails = null;
		try {
			top10StoriesDetails = iItemsRepository.getTop10Stories();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return top10StoriesDetails;
	}

	@Override
	public List<StoriesDTO> getAllRecordedItemsDetails() {
		List<StoriesDTO> allRecordedStoriesDetails = null;

		try {
			allRecordedStoriesDetails = iItemsRepository.getAllRecordedItemsDetails();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return allRecordedStoriesDetails;
	}

	@Scheduled(fixedRate = 15000L)
	@CacheEvict(value = "top10", allEntries = true)
	public void clearCache() {
	}

	@Override
	public List<CommentDTO> getTop10CommentsByStoryID(Integer id) {
		
		try {
			return iItemsRepository.getTop10CommentsByStoryID(id);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
