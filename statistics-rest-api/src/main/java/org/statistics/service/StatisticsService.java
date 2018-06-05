package org.statistics.service;

import org.statistics.model.StatisticsSummary;
import org.statistics.model.Transaction;

/**
 * @author rajeevmassey
 */
public interface StatisticsService {

    void computeStatistics(Transaction transaction);

    StatisticsSummary getStatistics();

}
