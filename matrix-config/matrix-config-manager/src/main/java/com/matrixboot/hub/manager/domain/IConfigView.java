package com.matrixboot.hub.manager.domain;

import java.io.Serializable;

/**
 * <p>
 * create in 2021/12/9 7:14 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IConfigView extends Serializable {

    /**
     * id
     *
     * @return id
     */
    Long getId();

    /**
     * namespace
     *
     * @return namespace
     */
    String getNamespace();

    /**
     * domain
     *
     * @return domain
     */
    String getDomain();

}
