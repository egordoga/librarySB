package ua.lib_sb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lib_sb.entity.Content;

@Repository
public interface IContentRepository extends JpaRepository<Content, Long> {
}
