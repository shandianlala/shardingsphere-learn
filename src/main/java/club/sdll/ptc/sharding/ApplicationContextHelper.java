package club.sdll.ptc.sharding;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring上下文工具类
 *
 * @author 草薙
 * @since 2022/3/8
 */
@Slf4j
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext context;

    /**
     * 注入ApplicationContext
     *
     * @param context 上下文
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        // 在加载Spring时自动获得context
        ApplicationContextHelper.context = context;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        if (context == null) {
            return null;
        }
        return (T) context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz) {
        if (context == null) {
            return null;
        }
        return context.getBean(clazz);
    }

}
