package com.redis.spring.springjedis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;



@SpringBootApplication
public class SpringjedisApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(SpringjedisApplication.class, args);


	}

	@Override
    public void run(String... args) {
		System.out.println("*********** Running Version 0.1 ***************");

		if (!(args.length ==  2 || args.length == 3)) {
			System.out.println("Usage: host port password");
			System.exit(1);
		}


		String host = args[0];
		int port = Integer.parseInt(args[1]);
		String password = null;
		if (args.length == 3) password = args[2];
		System.out.printf("%s %d %s \n", host, port, password);
		JedisPoolConfig config = new JedisPoolConfig();

		JedisPool pool = new JedisPool(config, host, port, Protocol.DEFAULT_TIMEOUT, password, true, null, null, null);
		try (Jedis jedis = pool.getResource()) {
			String resp = jedis.set("foo", "bar");
			System.out.println("Set:" + resp);
			System.out.println("Get: " + jedis.get("foo"));
		}
		//close the pool when application terminates
		pool.close();
	}

}
