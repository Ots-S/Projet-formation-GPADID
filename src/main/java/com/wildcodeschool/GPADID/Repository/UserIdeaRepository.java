package com.wildcodeschool.GPADID.Repository;

import com.wildcodeschool.GPADID.Entity.User;
import com.wildcodeschool.GPADID.Entity.UserIdea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserIdeaRepository extends JpaRepository<UserIdea, Long> {
    List<UserIdea> findAllByPurchasedByUser(User user);

}
