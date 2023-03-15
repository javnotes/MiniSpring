package com.minis.core;

import java.util.Iterator;

/**
 * 把外部的配置信息都当成资源(Resource)，
 * 比如 xml 文件，properties 文件，yaml 文件，json 文件等等
 */
public interface Resource extends Iterator<Object> {

}
