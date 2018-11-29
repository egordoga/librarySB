package ua.lib_sb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lib_sb.entity.Content;

public interface IContentRepository extends JpaRepository<Content, Long> {
}
