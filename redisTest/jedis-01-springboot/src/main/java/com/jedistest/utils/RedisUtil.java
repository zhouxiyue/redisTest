package com.jedistest.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public final class RedisUtil {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public boolean expire(String key,long time){
        try {
            if(time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public long getExpire(String key){
        try {
            Long t = redisTemplate.getExpire(key,TimeUnit.SECONDS);
            if(t==null){
                return 0;
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean hasKey(String keys){
        try {
            Boolean b = redisTemplate.hasKey(keys);
            if(b==null){
                return false;
            }
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void del(String... key){
        if(key!=null && key.length>0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                List keyList = CollectionUtils.arrayToList(key);
                redisTemplate.delete(keyList);
            }
        }
    }

    public String get(String key){
        return key==null?"":(String)redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 閫掑
     * @param key   閿?
     * @param delta 瑕佸鍔犲嚑(澶т簬0)
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("閫掑鍥犲瓙蹇呴』澶т簬0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }


    /**
     * 閫掑噺
     * @param key   閿?
     * @param delta 瑕佸噺灏戝嚑(灏忎簬0)
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("閫掑噺鍥犲瓙蹇呴』澶т簬0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }


    // ================================Map=================================

    /**
     * HashGet
     * @param key  閿?涓嶈兘涓簄ull
     * @param item 椤?涓嶈兘涓簄ull
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 鑾峰彇hashKey瀵瑰簲鐨勬墍鏈夐敭鍊?
     * @param key 閿?
     * @return 瀵瑰簲鐨勫涓敭鍊?
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     * @param key 閿?
     * @param map 瀵瑰簲澶氫釜閿€?
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * HashSet 骞惰缃椂闂?
     * @param key  閿?
     * @param map  瀵瑰簲澶氫釜閿€?
     * @param time 鏃堕棿(绉?
     * @return true鎴愬姛 false澶辫触
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 鍚戜竴寮爃ash琛ㄤ腑鏀惧叆鏁版嵁,濡傛灉涓嶅瓨鍦ㄥ皢鍒涘缓
     *
     * @param key   閿?
     * @param item  椤?
     * @param value 鍊?
     * @return true 鎴愬姛 false澶辫触
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 鍚戜竴寮爃ash琛ㄤ腑鏀惧叆鏁版嵁,濡傛灉涓嶅瓨鍦ㄥ皢鍒涘缓
     *
     * @param key   閿?
     * @param item  椤?
     * @param value 鍊?
     * @param time  鏃堕棿(绉? 娉ㄦ剰:濡傛灉宸插瓨鍦ㄧ殑hash琛ㄦ湁鏃堕棿,杩欓噷灏嗕細鏇挎崲鍘熸湁鐨勬椂闂?
     * @return true 鎴愬姛 false澶辫触
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 鍒犻櫎hash琛ㄤ腑鐨勫€?
     *
     * @param key  閿?涓嶈兘涓簄ull
     * @param item 椤?鍙互浣垮涓?涓嶈兘涓簄ull
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }


    /**
     * 鍒ゆ柇hash琛ㄤ腑鏄惁鏈夎椤圭殑鍊?
     *
     * @param key  閿?涓嶈兘涓簄ull
     * @param item 椤?涓嶈兘涓簄ull
     * @return true 瀛樺湪 false涓嶅瓨鍦?
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }


    /**
     * hash閫掑 濡傛灉涓嶅瓨鍦?灏变細鍒涘缓涓€涓?骞舵妸鏂板鍚庣殑鍊艰繑鍥?
     *
     * @param key  閿?
     * @param item 椤?
     * @param by   瑕佸鍔犲嚑(澶т簬0)
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }


    /**
     * hash閫掑噺
     *
     * @param key  閿?
     * @param item 椤?
     * @param by   瑕佸噺灏戣(灏忎簬0)
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }


    // ============================set=============================

    /**
     * 鏍规嵁key鑾峰彇Set涓殑鎵€鏈夊€?
     * @param key 閿?
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 鏍规嵁value浠庝竴涓猻et涓煡璇?鏄惁瀛樺湪
     *
     * @param key   閿?
     * @param value 鍊?
     * @return true 瀛樺湪 false涓嶅瓨鍦?
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }



    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0)
                expire(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }



    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }




    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ===============================list=================================


    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 閫氳繃绱㈠紩 鑾峰彇list涓殑鍊?
     *
     * @param key   閿?
     * @param index 绱㈠紩 index>=0鏃讹紝 0 琛ㄥご锛? 绗簩涓厓绱狅紝渚濇绫绘帹锛沬ndex<0鏃讹紝-1锛岃〃灏撅紝-2鍊掓暟绗簩涓厓绱狅紝渚濇绫绘帹
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0)
                expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
}
