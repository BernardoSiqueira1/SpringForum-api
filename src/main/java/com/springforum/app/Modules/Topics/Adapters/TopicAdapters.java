package com.springforum.app.Modules.Topics.Adapters;

import com.springforum.app.Modules.Topics.DTOs.GetTopicDTO;
import com.springforum.app.Modules.Topics.DTOs.NewTopicDTO;
import com.springforum.app.Modules.Topics.Model.Topics;
import org.springframework.data.domain.Page;

import java.util.List;

public class TopicAdapters {

    public static Topics toTopicEntity(String topicName){
        return new Topics(topicName);
    }

    public static List<GetTopicDTO> toTopicDTOList(Page<Topics> topicsPage){

        return topicsPage.stream().map( topic ->
                new GetTopicDTO(topic.getTopicId(), topic.getTopicName()))
                .toList();

    }

}
