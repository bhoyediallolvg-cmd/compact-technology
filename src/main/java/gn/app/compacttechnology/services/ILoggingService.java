package gn.app.compacttechnology.services;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @Author : DAOUDA BORY Yacouba Software Engineer
 * @Project : object-model
 * @Package : sn.finapps.guce.service
 * @Date : 11/06/2020
 * @Time : 14:26
 */
public interface ILoggingService {

    void logRequest(HttpServletRequest httpServletRequest, Object body);

    void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body);
}