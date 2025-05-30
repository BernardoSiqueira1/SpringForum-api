package com.springforum.app.Modules.Topics.Repository;

import com.springforum.app.Modules.Topics.Model.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TopicsRepository extends JpaRepository<Topics, Long> {

    @Query("SELECT t from Topics t where t.topicName = :topicName")
    public Optional<Topics> findByTopicName(@Param("topicName") String topicName);

}
