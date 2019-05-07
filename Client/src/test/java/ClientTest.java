import com.anvasy.controller.ArticleController;
import com.anvasy.controller.UsersController;
import com.anvasy.model.Article;
import com.anvasy.model.User;
import com.anvasy.rest.RestConnector;
import com.anvasy.rest.RestUserConnector;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ClientTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UsersController usersController;

    @InjectMocks
    private ArticleController articleController;

    @Mock
    private RestUserConnector restUserConnector = new RestUserConnector();

    @Mock
    private RestConnector restConnector = new RestConnector();


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(usersController, articleController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testSomeGetRequestsForUsers() throws Exception {
        this.mockMvc.perform(get("/login")).andExpect(status().isOk());
        this.mockMvc.perform(get("/register")).andExpect(status().isOk());

        User first = new User();
        User second = new User();

        when(restUserConnector.getUsers()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/admin")).andExpect(status().isOk());
    }

    @Test
    public void testSomeGetRequestsForArticles() throws Exception {
        Article first = new Article();
        Article second = new Article();

        when(restConnector.getArticleList()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/home")).andExpect(status().isOk());
    }

    @Test
    public void justForCoverage() {
        Article article = new Article(1);
        article.getSummary();
        article.getRateNumber();
        article.getRate();
        article.getContent();
        article.getTopic();
        article.getId();
        article.setRate(1);
        article.setRateNumber(1);
        article.setId(1);
        article.setSummary("a");
        article.setContent("a");
        article.setTopic("a");

        User user = new User("a", "a");
        user.setSurname("a");
        user.setId(1);
        user.setRole("a");
        user.setUsername("a");
        user.setPassword("a");
        user.setRegType("a");
        user.setName("a");
        user.getSurname();
        user.getId();
        user.getRole();
        user.getUsername();
        user.getPassword();
        user.getRegType();
        user.getName();
    }

}
