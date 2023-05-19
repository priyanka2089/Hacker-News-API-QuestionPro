package com.questionPro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.questionPro.model.StoriesDTO;

public interface IItemsRepositoryJPA extends JpaRepository<StoriesDTO, Integer>{

}
