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

  private final Map<Long, Post> posts;

  public PostRepository() {
    this.posts = new ConcurrentHashMap<>(){};
  }

  public List<Post> all() {
    List<Post> postsList = new ArrayList<>();
    for (Map.Entry<Long, Post> entry : posts.entrySet())
      postsList.add(entry.getValue());
    return postsList;
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      var newId = (long) posts.size() + 1;
      while (posts.containsKey(newId)) newId++;
      post.setId(newId);
    }
    posts.put(post.getId(), post);
    return post;
  }

  public void removeById(long id) {
    if (!posts.containsKey(id))
      throw new NotFoundException();
    posts.remove(id);
  }
}
