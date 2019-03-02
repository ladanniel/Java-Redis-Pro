//���ӳط�װ
package jedis_test;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
public class JedisPoolUtil {
	//�������ӳط�װ����
	public static JedisPool pool;
	public static Jedis jedis;
	//���ظ��ĳ���ִֻ��һ��
	static{
		//���������ļ� �Լ���������
	    InputStream in = JedisPoolUtil.class.getClassLoader().getResourceAsStream("redis.propertise");
		//������ȡ���������ļ�����
	    Properties propertise = new  Properties();
	    try {
			propertise.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(Integer.parseInt(propertise.get("redis.maxIdle").toString())); //������ø��� ����30���������ӣ��Զ��ر����ӳ�
		poolConfig.setMinIdle(Integer.parseInt(propertise.get("redis.minIdle").toString())); //��С���ø��� ���С��10���������ӣ��Զ�������������
		poolConfig.setMaxTotal(Integer.parseInt(propertise.get("redis.maxTotal").toString())); //���������
		 pool = new JedisPool(poolConfig,propertise.get("redis.url").toString(),Integer.parseInt(propertise.get("redis.port").toString()));
	}
	public static Jedis getJedisPool(){
		//����ȡ������Դ���ظ����÷�
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
		//ͨ��main�����������ӳ�
		Jedis jedis = getJedisPool();
		System.out.println(jedis.get("jackson"));
		JedisPoolUtil.colseJedis();
	}
	
	
	

}
