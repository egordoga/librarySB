package ua.lib_sb.serviceDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lib_sb.dao.IPublisherRepository;
import ua.lib_sb.entity.Publisher;

@Service
public class PublisherService implements IPublisherService {

    @Autowired
    private IPublisherRepository publisherRepository;

    @Override
    public Publisher findPublisherByName(String name) {
        return publisherRepository.findByName(name);
    }

    @Override
    public void savePublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }
}
