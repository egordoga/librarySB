package ua.lib_sb.serviceDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lib_sb.dao.IContentRepository;
import ua.lib_sb.entity.Content;

@Service
public class ContentService implements IContentService {
    @Autowired
    private IContentRepository contentRepository;

    @Override
    public void saveContent(Content content) {
        contentRepository.save(content);
    }
}
