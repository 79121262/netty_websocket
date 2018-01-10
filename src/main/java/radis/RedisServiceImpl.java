package radis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.logging.Logger;

public class RedisServiceImpl implements RedisService {
    public static final Integer TIMEOUT = 50;
    public static final Integer MAXREDIRECTIONS = 15;
    public static final Long MAXWAITMILLIS = 200L;
    public static final Integer MAXTOTAL = 5000;
    public static final Integer MINIDLE = 100;
    public static final Integer MAXIDLE = 500;
    private static final String DEFAULT_PATTERN = "*";
    private final Logger logger = Logger.getLogger(RedisServiceImpl.class.getName());
    private JedisCluster jedisCluster;

    @PostConstruct
    public void init() throws Exception {
        Set<HostAndPort> hostAndPort = null;//getHostAndPort();
        GenericObjectPoolConfig poolConfig = getGenericObjectPoolConfig();
        logger.info("init redis service,redis cluter size{}"+hostAndPort.size());
        jedisCluster = new JedisCluster(hostAndPort, TIMEOUT, MAXREDIRECTIONS, poolConfig);

        jedisCluster.save();
        //jedisCluster.d



    }

    private GenericObjectPoolConfig getGenericObjectPoolConfig() {
        GenericObjectPoolConfig gpc = new GenericObjectPoolConfig();
        gpc.setMaxWaitMillis(MAXWAITMILLIS);
        gpc.setMaxTotal(MAXTOTAL);
        gpc.setMinIdle(MINIDLE);
        gpc.setMaxIdle(MAXIDLE);
        return gpc;
    }

    public Set<String> getKeys(String pattern) {
        return null;
    }

    public Object getValueByKey(String key) {
        return null;
    }

    public Long delKey(String key) {
        return null;
    }
}
