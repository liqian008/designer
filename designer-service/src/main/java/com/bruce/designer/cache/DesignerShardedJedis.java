/**
 * $Id$
 * weibo.com . All rights reserved.
 */
package com.bruce.designer.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.util.Hashing;

/**
 * Comments for DesignerShardedJedis.java
 * 
 * @author <a href="mailto:liujun4@staff.sina.com.cn">刘军</a>
 * @createTime 2013-9-27 下午01:51:44
 */
public class DesignerShardedJedis extends ShardedJedis {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(DesignerShardedJedis.class);

    private ExecutorService executorService = new ThreadPoolExecutor(4, 12, 60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(10000));

    /**
     * @param shards
     */
    public DesignerShardedJedis(List<JedisShardInfo> shards) {
        super(shards);
    }

    public DesignerShardedJedis(List<JedisShardInfo> shards, Hashing algo) {
        super(shards, algo);
    }

    public DesignerShardedJedis(List<JedisShardInfo> shards, Pattern keyTagPattern) {
        super(shards, keyTagPattern);
    }

    public DesignerShardedJedis(List<JedisShardInfo> shards, Hashing algo, Pattern keyTagPattern) {
        super(shards, algo, keyTagPattern);
    }
    
    public Long del(List<String> keyList) {
        if (keyList != null && keyList.size() > 0) {
            if (keyList.size() == 1) {
                return del(keyList.get(0));
            } else {
                //多个分桶异步调用
                //根据id分桶
                Map<Jedis, List<String>> bucketMap = new HashMap<Jedis, List<String>>();
                for (String key : keyList) {
                    Jedis jedis = getShard(key);

                    List<String> bucketKeyList = bucketMap.get(jedis);
                    if (bucketKeyList == null) {
                        bucketKeyList = new ArrayList<String>();
                        bucketMap.put(jedis, bucketKeyList);
                    }
                    bucketKeyList.add(key);
                }
                //对桶做异步调用
                int bucketSize = bucketMap.size();
                final CountDownLatch countDownLatch = new CountDownLatch(bucketSize);
                List<Future<Long>> futureList = new ArrayList<Future<Long>>();
                for (Entry<Jedis, List<String>> bucketEntry : bucketMap.entrySet()) {
                    final Jedis jedis = bucketEntry.getKey();
                    final String[] keys = bucketEntry.getValue().toArray(new String[bucketSize]);
                    Future<Long> future = executorService
                            .submit(new Callable<Long>() {

                                @Override
                                public Long call() throws Exception {
                                    Long value = jedis.del(keys);
                                    countDownLatch.countDown();
                                    return value;
                                }

                            });
                    futureList.add(future);
                }

                boolean success = false;
                try {
                    success = countDownLatch.await(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    logger.error("mget(ShardedJedis, List<byte[]>)", e);
                }
                long number = 0;
                for (Future<Long> future : futureList) {
                    if (success || future.isDone()) {
                        try {
                            number += future.get();
                        } catch (InterruptedException e) {
                            logger.error("mget(ShardedJedis, List<byte[]>)", e);
                        } catch (ExecutionException e) {
                            logger.error("mget(ShardedJedis, List<byte[]>)", e);
                        }
                    } else {
                        future.cancel(true);
                    }
                   
                }
                return number;
            }
        }
        return 0l;
    }    

    public List<byte[]> mget(List<byte[]> byteKeyList) {
        List<byte[]> resultList = new ArrayList<byte[]>();
        if (byteKeyList != null && byteKeyList.size() > 0) {
            Map<String, byte[]> resultMap = mgetMap(byteKeyList);
            for (byte[] key : byteKeyList) {
                resultList.add(resultMap.get(new String(key)));
            }
        }
        return resultList;
    }

    public Map<String, byte[]> mgetMap(List<byte[]> byteKeyList) {
        Map<String, byte[]> resultMap = new HashMap<String, byte[]>();
        if (byteKeyList != null && byteKeyList.size() > 0) {
            if (byteKeyList.size() == 1) {
                get(byteKeyList.get(0));
            } else {
                //多个分桶异步调用
                //根据id分桶
                Map<Jedis, List<byte[]>> bucketMap = new HashMap<Jedis, List<byte[]>>();
                for (byte[] key : byteKeyList) {
                    Jedis jedis = getShard(key);

                    List<byte[]> bucketKeyList = bucketMap.get(jedis);
                    if (bucketKeyList == null) {
                        bucketKeyList = new ArrayList<byte[]>();
                        bucketMap.put(jedis, bucketKeyList);
                    }
                    bucketKeyList.add(key);
                }
                //对桶做异步调用
                int bucketSize = bucketMap.size();
                final CountDownLatch countDownLatch = new CountDownLatch(bucketSize);
                Map<byte[][], Future<List<byte[]>>> futureMap = new HashMap<byte[][], Future<List<byte[]>>>();
                for (Entry<Jedis, List<byte[]>> bucketEntry : bucketMap.entrySet()) {
                    final Jedis jedis = bucketEntry.getKey();
                    final byte[][] keys = bucketEntry.getValue().toArray(new byte[bucketSize][]);
                    Future<List<byte[]>> future = executorService
                            .submit(new Callable<List<byte[]>>() {

                                @Override
                                public List<byte[]> call() throws Exception {
                                    List<byte[]> value = jedis.mget(keys);
                                    countDownLatch.countDown();
                                    return value;
                                }

                            });
                    futureMap.put(keys, future);
                }

                boolean success = false;
                try {
                    success = countDownLatch.await(500, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    logger.error("mget(ShardedJedis, List<byte[]>)", e);
                }

                for (Entry<byte[][], Future<List<byte[]>>> futureEntry : futureMap.entrySet()) {
                    Future<List<byte[]>> future = futureEntry.getValue();
                    List<byte[]> dataList = null;
                    byte[][] keys = futureEntry.getKey();
                    if (success || future.isDone()) {
                        try {
                            dataList = future.get();
                        } catch (InterruptedException e) {
                            logger.error("mget(ShardedJedis, List<byte[]>)", e);
                        } catch (ExecutionException e) {
                            logger.error("mget(ShardedJedis, List<byte[]>)", e);
                        }
                    } else {
                        future.cancel(true);
                    }
                    if (dataList != null && dataList.size() == keys.length) {
                        int size = keys.length;
                        for (int i = 0; i < size; i++) {
                            resultMap.put(new String(keys[i]), dataList.get(i));
                        }
                    }
                }
            }
        }
        return resultMap;
    }

}
