package com.rbat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import javax.annotation.PostConstruct;

@Configuration
@DependsOn("mongoTemplate")
public class CollectionsConfig {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${document.player.expire:60}")
    long expireAfterSeconds;

    @PostConstruct
    public void setIndex(){
        mongoTemplate.indexOps("player").ensureIndex(
                new Index().on("lastPlayingTime", Sort.Direction.ASC)
                        .expire(expireAfterSeconds));
    }
}
