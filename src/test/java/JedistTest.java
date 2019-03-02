import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class JedistTest {
	@Test //jedis =>java + redis
	//��õ�һ��jedis����������ݿ�
	public void testJedist(){
		//1 ������Ӷ���(�Ի�����)
		Jedis jedis = new Jedis("localhost", 6379);
		// 2 �������
//		Object obj = jedis.get("username");
		//�洢Redis����
		jedis.set("user", "�����Ĳ���Redis");
		//��ȡ����
		Object obj = jedis.get("user");
		System.out.println(obj);
		
	}
	//ͨ��jedis��pool���jedis���Ӷ���
	@Test
	public void JedisPoolTest(){
		//1 �������ӳض���
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(30); //������ø��� ����30���������ӣ��Զ��ر����ӳ�
		poolConfig.setMinIdle(10); //��С���ø��� ���С��10���������ӣ��Զ�������������
		poolConfig.setMaxTotal(50); //���������
	//2 ����һ�� redis�����ӳ�
		JedisPool pool = new JedisPool(poolConfig, "localhost", 6379);
		//3 �����ӳ��л�ȡ��Դ
		Jedis jedis = pool.getResource();
		//4 ��������
		jedis.set("jackson", "�������ӳ�");
		System.out.println(jedis.get("jackson"));
		//5 �ر���Դ
		jedis.close();
		pool.close();
	}

}
