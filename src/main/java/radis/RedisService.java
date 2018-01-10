package radis;

import java.util.Set;

public interface RedisService {

    public Set<String> getKeys(String pattern);

    public Object getValueByKey(String key);

    public Long delKey(String key);

}