package baseJava.test.decorator;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonLogger extends DecoratorLogger {
    public JsonLogger(Logger logger) {
        super(logger);
    }

    @Override
    public void info(String msg) {

        JSONObject result = composeBasicJsonResult();
        result.put("MESSAGE", msg);
        logger.info(result.toString());
    }

    @Override
    public void error(String msg) {

        JSONObject result = composeBasicJsonResult();
        result.put("MESSAGE", msg);
        logger.error(result.toString());
    }

    public void error(Exception e) {

        JSONObject result = composeBasicJsonResult();
        result.put("EXCEPTION", e.getClass().getName());
        String exceptionStackTrace = ExceptionUtils.getStackTrace(e);
        result.put("STACKTRACE", exceptionStackTrace);
        logger.error(result.toString());
    }

    public static class JsonLoggerFactory {

        @SuppressWarnings("rawtypes")
        public static JsonLogger getLogger(Class clazz) {

            Logger logger = LoggerFactory.getLogger(clazz);
            return new JsonLogger(logger);
        }
    }

    private JSONObject composeBasicJsonResult() {
        //拼装了一些运行时信息
        return null;
    }
}