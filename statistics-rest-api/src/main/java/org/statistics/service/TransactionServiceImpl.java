package org.statistics.service;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.statistics.common.APIError;
import org.statistics.exception.ValidationException;
import org.statistics.model.Transaction;

/**
 * Holds business logic for processing transactions
 *
 * @author rajeevmassey
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	private StatisticsService statisticsService;

	@Override
	public void addTransaction(Transaction transaction) {
		if (transaction == null)
			throw new ValidationException(APIError.VALIDATION_EMPTY_REQUEST_BODY);
		if (transaction.getTimestamp() == null)
			throw new ValidationException(APIError.VALIDATION_MISSING_TIMESTAMP);
		if (transaction.getAmount() == null)
			throw new ValidationException(APIError.VALIDATION_MISSING_AMOUNT);
		Instant instant = Instant.now();
		if (instant.toEpochMilli() - transaction.getTimestamp() > 60)
			throw new ValidationException(APIError.VALIDATION_EXPIRED_TIMESTAMP);

		logger.info("Received new transaction => {}", transaction);
		statisticsService.computeStatistics(transaction);
	}
}
