import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class JedistTest {
	@Test //jedis =>java + redis
	//获得单一的jedis对象操作数据库
	public void testJedist(){
		//1 获得连接对象(对话对象)
		Jedis jedis = new Jedis("localhost", 6379);
		// 2 获得数据
//		Object obj = jedis.get("username");
		//存储Redis数据
		jedis.set("user", "张三的测试Redis");
		//获取数据
		Object obj = jedis.get("user");
		System.out.println(obj);
		
	}
	//通过jedis的pool获得jedis连接对象
	@Test
	public void JedisPoolTest(){
		//1 创建连接池对象
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(30); //最大闲置个数 大于30个闲置连接，自动关闭连接池
		poolConfig.setMinIdle(10); //最小闲置个数 如果小于10个闲置连接，自动增加连接数量
		poolConfig.setMaxTotal(50); //最大连接数
	//2 创建一个 redis的连接池
		JedisPool pool = new JedisPool(poolConfig, "localhost", 6379);
		//3 从连接池中获取资源
		Jedis jedis = pool.getResource();
		//4 操作数据
		jedis.set("jackson", "测试连接池");
		System.out.println(jedis.get("jackson"));
		//5 关闭资源
		jedis.close();
		pool.close();
	}

}
