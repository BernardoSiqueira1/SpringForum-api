package com.springforum.app.Modules.Topics.Repository;

import com.springforum.app.Modules.Topics.Model.Topics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicsRepository extends JpaRepository<Topics, Long> {



}
