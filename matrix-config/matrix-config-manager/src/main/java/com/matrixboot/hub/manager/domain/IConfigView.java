package com.matrixboot.hub.manager.domain;

/**
 * <p>
 * create in 2021/12/9 7:14 PM
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IConfigView {

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
