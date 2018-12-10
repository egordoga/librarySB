package ua.lib_sb.services.serviceDB;

import ua.lib_sb.entity.Publisher;

public interface IPublisherService {
    Publisher findPublisherByName(String name);

    void savePublisher(Publisher publisher);
}
