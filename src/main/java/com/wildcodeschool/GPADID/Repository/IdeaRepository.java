package com.wildcodeschool.GPADID.Repository;

import com.wildcodeschool.GPADID.Entity.Idea;
import com.wildcodeschool.GPADID.Entity.Theme;
import com.wildcodeschool.GPADID.Entity.User;
import com.wildcodeschool.GPADID.Entity.UserIdea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {
    List<Idea> findByThemeId(Long id);
    List<Idea> findByThemeAndUserIsNot(Theme theme, User user);

}
