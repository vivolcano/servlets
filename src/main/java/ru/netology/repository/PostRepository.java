package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepository {

  private final Map<Long, Post> posts = new ConcurrentHashMap<>(){};
  private final AtomicLong counter = new AtomicLong(0L);

  public List<Post> all() {
    return new ArrayList<>(posts.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

  public Post save(Post post) {
    if (post.getId() != 0 && !posts.containsKey(post.getId())) {
      throw new NotFoundException();
    }
    
    if (post.getId() == 0 {
      var newId = counter.incrementAndGet();
      post.setId(newId)
    }
        
    posts.put(post.getId(), post)
        return post;
    
  }
}
