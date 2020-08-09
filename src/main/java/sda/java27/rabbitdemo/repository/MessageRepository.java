package sda.java27.rabbitdemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sda.java27.rabbitdemo.data.MessageDB;

public interface MessageRepository extends MongoRepository<MessageDB, String> {

}
