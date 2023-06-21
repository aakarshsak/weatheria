package com.sinha.batchjobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CityProcessor implements ItemProcessor<City, City> {

    Logger logger = LoggerFactory.getLogger(CityProcessor.class);

    @Override
    public City process(City item) throws Exception {

        logger.info(item.toString());
        return item;
    }
}
