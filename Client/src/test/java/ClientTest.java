import com.anvasy.controller.ArticleController;
import com.anvasy.controller.UsersController;
import com.anvasy.model.Article;
import com.anvasy.model.User;
import com.anvasy.rest.RestConnector;
import com.anvasy.rest.RestUserConnector;
import com.anvasy.utils.OAuthUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void testSomeRequestsForUsers() throws Exception {
        mockMvc.perform(get("/login")).andExpect(status().isOk());
        mockMvc.perform(get("/register")).andExpect(status().isOk());

        User first = new User();
        User second = new User();

        when(restUserConnector.getUsers()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/admin")).andExpect(status().isOk());
        mockMvc.perform(post("/logout")).andExpect(status().isFound());

        when(restUserConnector.logInOrRegister(Matchers.any(User.class), anyString())).thenReturn(null);

        mockMvc.perform(post("/register").requestAttr("user", new User())).andExpect(status().isOk());
        mockMvc.perform(post("/login").requestAttr("user", new User())).andExpect(status().isFound());

        doNothing().when(restUserConnector).changeRole(anyString(), anyString());

        mockMvc.perform(post("/changerole").param("role", "role").param("username",
                "username")).andExpect(status().isFound());

        User user = new User("username", "name", "surname", "regtype");
    }

    @Test
    public void testSomeRequestsForArticles() throws Exception {
        Article first = new Article();
        Article second = new Article();

        when(restConnector.getArticleList()).thenReturn(Arrays.asList(first, second));
        when(restConnector.getArticle(anyInt())).thenReturn(new Article());
        doNothing().when(restConnector).updateArticle(Matchers.any(Article.class));
        doNothing().when(restConnector).removeArticle(anyInt());

        mockMvc.perform(get("/home")).andExpect(status().isOk());
        mockMvc.perform(get("/addedit").param("id", "0")).andExpect(status().isOk());
        mockMvc.perform(get("/article").param("id", "0")).andExpect(status().isOk());
        mockMvc.perform(post("/article").param("id", "0")).andExpect(status().isFound());
        mockMvc.perform(post("/addedit").requestAttr("article", new Article())).andExpect(status().isFound());
    }

    @Test
    public void testArticleAndUser() {
        Article article = new Article(1);
        article.getSummary();
        article.getRate();
        article.getContent();
        article.getTopic();
        article.getId();

        User user = new User();
        user.setName("a");
        user.setPassword("password");
        user.setRole("admin");
        user.setUsername("username");
        user.getSurname();
        user.getId();
        user.getRole();
        user.getUsername();
        user.getPassword();
        user.getRegType();
        user.getName();
    }

}
