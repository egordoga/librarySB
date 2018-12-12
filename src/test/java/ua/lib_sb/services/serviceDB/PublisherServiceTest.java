package ua.lib_sb.services.serviceDB;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.lib_sb.dao.IPublisherRepository;
import ua.lib_sb.entity.Publisher;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PublisherServiceTest {

    @Autowired
    private PublisherService publisherService;
    @MockBean
    private IPublisherRepository publisherRepository;

    private Publisher publisher;


    @Before
    public void setUp() {
        publisher = new Publisher("aa");

    }

    @Test
    public void findPublisherByName() {
    }

    @Test
    public void savePublisher() {
    }
}