package com.redis.spring.springjedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;


@SpringBootApplication
public class SpringjedisApplication implements CommandLineRunner {

	@Autowired
	JedisConnectionFactory jcf;

	public static void main(String[] args) {
		SpringApplication.run(SpringjedisApplication.class, args);
	}

	@Override
    public void run(String... args) {
        System.out.println("Hello world");
		Jedis  j = (Jedis)jcf.getConnection().getNativeConnection();
		j.set("foo", "bar");
		j.close();
	}

}
