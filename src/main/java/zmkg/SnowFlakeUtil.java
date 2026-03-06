package zmkg;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 雪花算法获取分布式ID
 *
 **/
@Slf4j
public class SnowFlakeUtil {

    /**
     * 机房ID
     */
    private   static  long workerId = 0;
    /**
     * 机器ID
     */
    private  static long datacenterId = 1;

    private static Snowflake snowflake = IdUtil.createSnowflake( workerId, datacenterId );



    /**
     * 无参获取通过默认机房机器号获取id
     *
     * @return
     */
    public static synchronized long snowflakeId() {
        return snowflake.nextId();
    }
    public static synchronized String getSnowId() {
        return String.valueOf(snowflake.nextId());
    }

    /**
     * 有参根据参数获取ID
     *
     * @param workerId     机房ID
     * @param datacenterId 机器ID
     * @return
     */
    public static synchronized long snowflakeId(long workerId, long datacenterId) {
        Snowflake snowflake = IdUtil.createSnowflake( workerId, datacenterId );
        return snowflake.nextId();
    }


    public static void main(String[] args) {
        for(long i=0;i<10000000000000L;i++){
            System.out.println(getSnowId());
        }
    }


}
