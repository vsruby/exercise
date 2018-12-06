package com.vince.exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vince.exercise.elasticsearch.BookESRepository;
import com.vince.exercise.elasticsearch.MemberESRepository;

@Component
public class SyncDatabaseAndElasticsearch implements CommandLineRunner {

    private Logger logger;

    private BookESRepository booksES;
    private MemberESRepository membersES;

    public SyncDatabaseAndElasticsearch(BookESRepository booksES, MemberESRepository membersES) {
        this.booksES = booksES;
        this.membersES = membersES;

        this.logger = LoggerFactory.getLogger(SyncDatabaseAndElasticsearch.class);
    }

    @Override
    public void run(String... strings) throws Exception {
        logger.info("Started syncing database and elasticsearch...");

        /* On Startup the database will be emtpy so for now I can just delete all records in the elasticsearch indicies */
        try {
            this.booksES.deleteAll();
            this.membersES.deleteAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        logger.info("Finished syncing database and elasticsearch.");
    }
}
