package com.questionPro.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.questionPro.model.CommentDTO;
import com.questionPro.model.StoriesDTO;

@Repository
public class ItemsRepositoryImpl implements IItemsRepository {

	@Autowired
	IItemsRepositoryJPA iItemsRepositoryJPA;

	@Override
	public List<StoriesDTO> getTop10Stories() throws Exception {
		// call to Hacker API to fetch top 10 stories.
		List<Integer> allTopStories = null;
		List<StoriesDTO> top10StoryDetails = new ArrayList<StoriesDTO>();
		System.out.println("############### STATEMENT 1 # :- ");
		var uri = URI.create("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder().uri(uri).GET().build();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println("############### STATEMENT 2 # :- ");
		ObjectMapper mapper = new ObjectMapper();
		allTopStories = mapper.readValue(response.body(), List.class);
		allTopStories = allTopStories.stream().limit(10).toList();

		for (Integer s : allTopStories) {
			System.out.println("############### STATEMENT 5 # :- " + s);
		}
		System.out.println("############### STATEMENT 3 # :- " + allTopStories.size());
		top10StoryDetails = getStoryDetails(allTopStories);

		return top10StoryDetails;
	}

	@Override
	public List<StoriesDTO> getStoryDetails(List<Integer> ids) throws Exception {
		List<StoriesDTO> storiesDetails = new ArrayList<StoriesDTO>();

		for (Integer id : ids) {

			var uri = URI.create("https://hacker-news.firebaseio.com/v0/item/" + id.toString() + ".json");
			var client = HttpClient.newHttpClient();
			var request = HttpRequest.newBuilder().uri(uri).GET().build();
			var response = client.send(request, HttpResponse.BodyHandlers.ofString());
			ObjectMapper mapper = new ObjectMapper();
			StoriesDTO detail = mapper.readValue(response.body(), StoriesDTO.class);
			storiesDetails.add(detail);
			if (iItemsRepositoryJPA.existsById(detail.getId())) {
				iItemsRepositoryJPA.deleteById(id);
			}
			iItemsRepositoryJPA.save(detail);
		}

		return storiesDetails;
	}

	@Override
	public List<StoriesDTO> getAllRecordedItemsDetails() throws Exception {
		List<StoriesDTO> storiesDetails = new ArrayList<StoriesDTO>();
		storiesDetails = iItemsRepositoryJPA.findAll();
		return storiesDetails;
	}

	@Override
	public List<CommentDTO> getTop10CommentsByStoryID(Integer id) throws Exception {
		List<Integer> top10CommentsIDs = new ArrayList<Integer>();
		List<CommentDTO> top10Comments = new ArrayList<CommentDTO>();
		ObjectMapper mapper;
		URI uri;
		HttpClient client;
		HttpRequest request;
		uri = URI.create("https://hacker-news.firebaseio.com/v0/item/" + id + ".json");
		client = HttpClient.newHttpClient();
		request = HttpRequest.newBuilder().uri(uri).GET().build();
		var response = client.send(request, HttpResponse.BodyHandlers.ofString());
		mapper = new ObjectMapper();
		StoriesDTO storyDetail = mapper.readValue(response.body(), StoriesDTO.class);
		top10CommentsIDs = (storyDetail.getKids() == null || storyDetail.getKids().isEmpty()) ? null
				: storyDetail.getKids().stream().limit(10).toList();
		if (top10CommentsIDs != null && top10CommentsIDs.size() > 0) {
			for (Integer commentid : top10CommentsIDs) {

				uri = URI.create("https://hacker-news.firebaseio.com/v0/item/" + commentid.toString() + ".json");
				client = HttpClient.newHttpClient();
				request = HttpRequest.newBuilder().uri(uri).GET().build();
				var commentsresponse = client.send(request, HttpResponse.BodyHandlers.ofString());
				mapper = new ObjectMapper();
				CommentDTO detail = mapper.readValue(commentsresponse.body(), CommentDTO.class);
				top10Comments.add(detail);

			}
            //Sorting in ascending order
			Comparator<CommentDTO> Comparator = new Comparator<CommentDTO>() {
				@Override
				public int compare(CommentDTO i1, CommentDTO i2) {
					int i1_kids_size = i1.getKids() != null ? i1.getKids().size() : 0;
					int i2_kids_size = i2.getKids() != null ? i2.getKids().size() : 0;
					return i1_kids_size - i2_kids_size;
				}
			};

			top10Comments = top10Comments.stream().sorted(Comparator).toList();
			return top10Comments;

		}

		return null;
	}

}
