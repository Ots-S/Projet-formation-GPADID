package com.wildcodeschool.GPADID.Repository;

import com.wildcodeschool.GPADID.Entity.Idea;
import com.wildcodeschool.GPADID.Entity.User;
import com.wildcodeschool.GPADID.Entity.UserIdea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserIdeaRepository extends JpaRepository<UserIdea, Long> {
    List<UserIdea> findAllByPurchasedByUser(User user);
    Optional<UserIdea> findByPurchasedIdeaAndPurchasedByUser(Idea idea, User user);
    UserIdea findByPurchasedIdea(Idea idea);
}
