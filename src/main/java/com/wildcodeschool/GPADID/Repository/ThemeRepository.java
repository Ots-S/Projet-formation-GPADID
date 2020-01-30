package com.wildcodeschool.GPADID.Repository;

import com.wildcodeschool.GPADID.Entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
}
