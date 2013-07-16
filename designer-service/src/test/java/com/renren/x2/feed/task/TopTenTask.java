package com.renren.x2.feed.task;

import java.util.List;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.renren.x2.feed.service.IFeedWriterService;
import com.renren.x2.feed.storage.IFeedReaderStorage;

public class TopTenTask extends TimerTask {

    private final Logger logger = Logger.getLogger(TopTenTask.class);

    private final int LIMIT = 1000;

    /** feed 存储相关 */

    private IFeedReaderStorage feedReaderStorage;

    private IFeedWriterService feedWriterService;

    @Override
    public void run() {
	
		logger.debug("timerTask begin");

        List<Integer> schoolIds;

        schoolIds = feedReaderStorage.getSchoolList();

        if ((null != schoolIds) && (schoolIds.size() > 0)) {
			logger.debug("get schoolIds begin with " + schoolIds.get(0));
            int size = schoolIds.size();
            for (int i = 0; i < size; i++) {
                int schoolId = schoolIds.get(i);
                feedWriterService.updateSchoolTopTen(schoolId, LIMIT);
            }
        }

		logger.debug("timerTask end");
    }

    /**
     * @param feedReaderStorage the feedReaderStorage to set
     */
    public void setFeedReaderStorage(IFeedReaderStorage feedReaderStorage) {
        this.feedReaderStorage = feedReaderStorage;
    }

    /**
     * @param feedWriterService the feedWriterService to set
     */
    public void setFeedWriterService(IFeedWriterService feedWriterService) {
        this.feedWriterService = feedWriterService;
    }

}
