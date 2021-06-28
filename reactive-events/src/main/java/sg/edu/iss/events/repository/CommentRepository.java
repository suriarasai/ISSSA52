package sg.edu.iss.events.repository;

import reactor.core.publisher.Flux;
import sg.edu.iss.events.model.Comment;

public interface CommentRepository {

    Flux<Comment> findAll();

}
