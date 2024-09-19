package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.ErrorLog;
import org.example.repositories.ErrorLogRepository;
import org.example.services.ErrorLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ErrorLogServiceImpl implements ErrorLogService {

    private final ErrorLogRepository errorLogRepository;


    @Transactional
    @Async
    public void saveError(ErrorLog errorLog) {

        Optional<ErrorLog> existingErrorLog = errorLogRepository.findByErrorMessage(errorLog.getErrorMessage());
        if (existingErrorLog.isPresent()) {
            ErrorLog oldErrorLog = existingErrorLog.get();
            oldErrorLog.setErrorCount(oldErrorLog.getErrorCount() + 1);
            errorLogRepository.save(oldErrorLog);
        } else {
            ErrorLog newErrorLog = new ErrorLog();
            newErrorLog.setErrorMessage(errorLog.getErrorMessage());
            newErrorLog.setErrorClass(errorLog.getErrorClass());
            newErrorLog.setErrorCount(1);
            errorLogRepository.save(newErrorLog);
        }
    }

    @Override
    public List<ErrorLog> getErrorLog() {
        return errorLogRepository.findAll();
    }

}
