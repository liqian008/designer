/**
 * Copyright 2009-2011 Oak Pacific Interactive. All rights reserved.
 */
package com.renren.x2.feed.jade.dao;

import com.xiaonei.jade.datasource.Catalogs;
import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;


/**   
 * @author <a href="mailto:wp.daizhize@renren-inc.com">Dai,Zhize</a>
 * Created at 2012-10-26 下午02:09:06   
 */
@DAO(catalog = Catalogs.IDSEQUENCE)
public interface SequenceJadeDAO {

    public static final String SEQ_FEED_ID = "x2_feed_id_seq";
    
    public static final String SEQ_UGC_ID = "x2_ugc_id_seq";
    
    @SQL("select nextval('##(:name)'::text)")
    public long nextId( // NL
                        @SQLParam("name") String name);
}
 

