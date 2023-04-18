package com.redis.spring.springjedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class SimpleTLS {

    private String host;
    private int port;
    private String password;

    public static void main(String args[]) {
        if (!(args.length ==  2 || args.length == 3)) {
            System.out.println("Usage: host port password");
            System.exit(1);
        }

        SimpleTLS example = new SimpleTLS();
        example.host = args[0];
        example.port = Integer.parseInt(args[1]);
        if (args.length == 3) example.password = args[2];

         JedisPoolConfig config = new JedisPoolConfig();

        JedisPool pool = new JedisPool(config, example.host, example.port, Protocol.DEFAULT_TIMEOUT, example.password, true,null, null, null);
        try (Jedis jedis = pool.getResource()) {
            String resp = jedis.set("foo", "bar");
            System.out.println("Set:" + resp);
            System.out.println("Get: " + jedis.get("foo"));
        }
        //close the pool when application terminates
        pool.close();
    }
}
