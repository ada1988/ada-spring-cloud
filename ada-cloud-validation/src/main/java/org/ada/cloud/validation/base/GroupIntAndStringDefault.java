package org.ada.cloud.validation.base;

import javax.validation.GroupSequence;

/**  
 * Filename: GroupIntAndStringDefault.java  <br>
 *
 * Description: 顺序固定、组合验证  <br>
 * 
 * @author: CZD <br> 
 * @version: 1.0 <br> 
 * @Createtime: 2017年6月6日 <br>
 *
 *  
 */
@GroupSequence( { IntParamNotNullDefault.class, MobileParamNotNullDefault.class })  
public interface GroupIntAndStringDefault {

}
