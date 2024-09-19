package org.example.logginghttpspringbootstarter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jdk.jfr.Description;
import org.example.logginghttpspringbootstarter.config.LoggingProperties;
import org.example.logginghttpspringbootstarter.filter.LoggingFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoggingFilterTest {
    private LoggingFilter filter;

    @BeforeEach
    void setup() {
        LoggingProperties properties = new LoggingProperties();
        properties.setLevel("DEBUG");
        properties.setFormat("json");

        filter = new LoggingFilter(properties);
    }

    @Test
    @Description("Проверяет, как фильтр работает при отсутствии заголовков и параметров запроса")
    void testEmptyHeadersAndParams() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setRequestURI("/test");

        MockHttpServletResponse response = new MockHttpServletResponse();

        assertDoesNotThrow(() -> filter.doFilter(request, response, (req, res) -> {}));
    }

    @Test
    @Description("Проверяет логирование для HTTP-метода, который не часто используется, например PATCH")
    void testUnsupportedHttpMethod() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("PATCH");
        request.setRequestURI("/test");

        MockHttpServletResponse response = new MockHttpServletResponse();

        assertDoesNotThrow(() -> filter.doFilter(request, response, (req, res) -> {}));
    }

    @Test
    @Description("Проверяет, как фильтр справляется с большими телами запросов")
    void testLargeRequestBody() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("POST");
        request.setRequestURI("/test");
        request.setContent("Large body content".repeat(10000).getBytes());

        MockHttpServletResponse response = new MockHttpServletResponse();

        assertDoesNotThrow(() -> filter.doFilter(request, response, (req, res) -> {}));
    }

    @Test
    @Description("Проверяет, что фильтр корректно обрабатывает исключения, возникшие во время выполнения запроса")
    void testServerErrorResponse() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setRequestURI("/test");

        MockHttpServletResponse response = new MockHttpServletResponse();
        response.setStatus(500); // Симуляция ошибки сервера

        assertDoesNotThrow(() -> filter.doFilter(request, response, (req, res) -> {}));
    }

    @Test
    @Description("Проверяет работу фильтра в случае ошибки на стороне сервера.")
    void testExceptionDuringProcessing() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.setRequestURI("/test");

        MockHttpServletResponse response = new MockHttpServletResponse();

        FilterChain filterChain = (req, res) -> {
            throw new ServletException("Test Exception");
        };

        assertThrows(ServletException.class, () -> filter.doFilter(request, response, filterChain));
    }
}
