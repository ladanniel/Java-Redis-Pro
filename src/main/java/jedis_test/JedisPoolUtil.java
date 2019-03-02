//连接池封装
package jedis_test;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
public class JedisPoolUtil {
	//创建连接池封装对象
	public static JedisPool pool;
	public static Jedis jedis;
	//让重复的程序只执行一次
	static{
		//加载配置文件 以及返回类型
	    InputStream in = JedisPoolUtil.class.getClassLoader().getResourceAsStream("redis.propertise");
		//创建读取加载配置文件对象
	    Properties propertise = new  Properties();
	    try {
			propertise.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(Integer.parseInt(propertise.get("redis.maxIdle").toString())); //最大闲置个数 大于30个闲置连接，自动关闭连接池
		poolConfig.setMinIdle(Integer.parseInt(propertise.get("redis.minIdle").toString())); //最小闲置个数 如果小于10个闲置连接，自动增加连接数量
		poolConfig.setMaxTotal(Integer.parseInt(propertise.get("redis.maxTotal").toString())); //最大连接数
		 pool = new JedisPool(poolConfig,propertise.get("redis.url").toString(),Integer.parseInt(propertise.get("redis.port").toString()));
	}
	public static Jedis getJedisPool(){
		//将获取到的资源返回给调用方
		return pool.getResource();
	}
	public static void colseJedis(){
		if(jedis!=null){
			jedis.close();
		}
		if (pool!=null) {
			pool.close();
		}
	}
	public static void main(String[] args) {
		//通过main方法测试连接池
		Jedis jedis = getJedisPool();
		System.out.println(jedis.get("jackson"));
		JedisPoolUtil.colseJedis();
	}
	
	
	

}
