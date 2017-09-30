package com.statistics.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Transaction is expired")
public class ExpiredTransactionException extends RuntimeException {
}
