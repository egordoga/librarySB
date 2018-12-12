package ua.lib_sb.services.serviceDB;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.lib_sb.dao.IContentRepository;
import ua.lib_sb.entity.Content;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ContentServiceTest {

    @Autowired
    private ContentService contentService;
    @MockBean
    private IContentRepository contentRepository;

    @Test
    public void saveContent() {
        Content content = new Content();
        contentService.saveContent(content);
        verify(contentRepository, times(1)).save(content);
    }
}