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
                    logger.error("mget(ShardedJedis, List<String>)", e);
                }
                long number = 0;
                for (Future<Long> future : futureList) {
                    if (success || future.isDone()) {
                        try {
                            number += future.get();
                        } catch (InterruptedException e) {
                            logger.error("mget(ShardedJedis, List<String>)", e);
                        } catch (ExecutionException e) {
                            logger.error("mget(ShardedJedis, List<String>)", e);
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

    public List<String> mget(List<String> keyList) {
        List<String> resultList = new ArrayList<String>();
        if (keyList != null && keyList.size() > 0) {
            Map<String, String> resultMap = mgetMap(keyList);
            for (String key : keyList) {
                resultList.add(resultMap.get(new String(key)));
            }
        }
        return resultList;
    }

    public Map<String, String> mgetMap(List<String> keyLIst) {
        Map<String, String> resultMap = new HashMap<String, String>();
        if (keyLIst != null && keyLIst.size() > 0) {
            if (keyLIst.size() == 1) {
                get(keyLIst.get(0));
            } else {
                //多个分桶异步调用
                //根据id分桶
                Map<Jedis, List<String>> bucketMap = new HashMap<Jedis, List<String>>();
                for (String key : keyLIst) {
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
                Map<String[], Future<List<String>>> futureMap = new HashMap<String[], Future<List<String>>>();
                for (Entry<Jedis, List<String>> bucketEntry : bucketMap.entrySet()) {
                    final Jedis jedis = bucketEntry.getKey();
                    final String[] keys = bucketEntry.getValue().toArray(new String[bucketSize]);
                    Future<List<String>> future = executorService
                            .submit(new Callable<List<String>>() {

                                @Override
                                public List<String> call() throws Exception {
                                    List<String> value = jedis.mget(keys);
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
                    logger.error("mget(ShardedJedis, List<String>)", e);
                }

                for (Entry<String[], Future<List<String>>> futureEntry : futureMap.entrySet()) {
                    Future<List<String>> future = futureEntry.getValue();
                    List<String> dataList = null;
                    String[] keys = futureEntry.getKey();
                    if (success || future.isDone()) {
                        try {
                            dataList = future.get();
                        } catch (InterruptedException e) {
                            logger.error("mget(ShardedJedis, List<String>)", e);
                        } catch (ExecutionException e) {
                            logger.error("mget(ShardedJedis, List<String>)", e);
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
