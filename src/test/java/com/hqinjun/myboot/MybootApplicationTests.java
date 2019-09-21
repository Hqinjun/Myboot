package com.hqinjun.myboot;

import com.hqinjun.myboot.domain.Spermission;
import com.hqinjun.myboot.domain.User;
import com.hqinjun.myboot.domain.es.Books;
import com.hqinjun.myboot.repository.SpermissionRepository;
import com.hqinjun.myboot.repository.UserRepository;
import com.hqinjun.myboot.repository.es.BooksEsRepository;
import com.hqinjun.myboot.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybootApplicationTests {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private BooksEsRepository booksEsRepository;

	@Autowired
	private UserRepository repository;

	@Autowired
	private SpermissionRepository spermissionRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RedisUtil redisUtil;


	@Autowired
	private JedisPool jedisPool;

	@Test
	public void contextLoads() {
		List <User> userList = repository.findAll();
		for (User user : userList){
			System.out.println(user.toString());
		}

	}

	@Test
	public void getuser(){
		List<Spermission> permissions = spermissionRepository.getAdminPermission(1);
		System.out.println("11");
	}


	@Test
	public void getPassword(){
		String password = passwordEncoder.encode("admin");
		System.out.println(password);
	}

	@Test
	public void getRedis(){
//		Jedis jedis=  jedisPool.getResource();
////		jedis.select(0);
////		Set<String> str =  jedis.keys("*");
////		for (String string : str) {
////			System.out.println(string);
////		}

		Set<String> str =  redisUtil.keys("*");
		for (String string : str) {
			System.out.println(string);
		}
	}

	@Test
	public void createIndex(){
		elasticsearchTemplate.createIndex(Books.class);
	}

	@Test
	public void savees(){
		Books books = new Books();
		books.setId(1L);
		books.setAuthor("hqinjun");
		books.setChapter(1L);
		books.setContent("这是一本修仙小说");
		books.setTitle("开始修仙");
		booksEsRepository.save(books);

	}

}
