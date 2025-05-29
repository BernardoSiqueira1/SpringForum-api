package com.springforum.app.Modules.Topics.Services;

import com.springforum.app.Modules.Topics.Adapters.TopicAdapters;
import com.springforum.app.Modules.Topics.DTOs.GetTopicDTO;
import com.springforum.app.Modules.Topics.DTOs.NewTopicDTO;
import com.springforum.app.Modules.Topics.Model.Topics;
import com.springforum.app.Modules.Topics.Repository.TopicsRepository;
import jakarta.transaction.Transactional;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicsService {

    @Autowired
    private TopicsRepository topicsRepository;

    @Transactional
    public void createNewTopic(NewTopicDTO newTopicDTO){

        Topics newTopic = TopicAdapters.toTopicEntity(newTopicDTO.topicName());

        topicsRepository.save(newTopic);

    }

    public List<GetTopicDTO> getTopicsPage(int pageNumber){
        Pageable queryPage = PageRequest.of(pageNumber, 5);

        return TopicAdapters.toTopicDTOList(topicsRepository.findAll(queryPage));
    }

    @Transactional
    public void deleteExistingTopic(long topicId){
        topicsRepository.deleteById(topicId);
    }

}
