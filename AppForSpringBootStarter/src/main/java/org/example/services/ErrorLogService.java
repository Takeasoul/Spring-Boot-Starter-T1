package org.example.services;


import org.example.entity.ErrorLog;

import java.util.List;
public interface ErrorLogService {

    void saveError(ErrorLog errorLog);

    List<ErrorLog> getErrorLog();
}
