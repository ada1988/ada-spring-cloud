package org.ada.cloud.redis.session.init;

import org.ada.cloud.redis.session.config.MdConfig;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**  
 * Filename: TEst.java  <br>
 *
 * Description:  基于servlet 3.0 自动发现机制SPI，对外提供的初始化接口，实现springSessionRepositoryFilter注入，以及MdConfig加载 <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年5月31日 <br>
 *
 *  
 */

public class MdInitializer extends AbstractHttpSessionApplicationInitializer { 
    public MdInitializer() {
            super(MdConfig.class); 
    }
}
