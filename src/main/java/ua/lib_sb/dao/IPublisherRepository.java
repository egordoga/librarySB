package ua.lib_sb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lib_sb.entity.Publisher;

public interface IPublisherRepository extends JpaRepository<Publisher, Long> {
    Publisher findByName(String name);

}
