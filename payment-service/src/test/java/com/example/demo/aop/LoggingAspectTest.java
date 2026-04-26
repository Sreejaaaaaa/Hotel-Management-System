package com.example.demo.aop;

import com.example.demo.service.TestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class LoggingAspectTest {

    @Autowired
    private TestService testService;

    @Test
    void testSuccessLogs(CapturedOutput output) {
        testService.successMethod();

        assertThat(output.getOut())
                .contains("Entering: successMethod")
                .contains("Exiting: successMethod")
                .contains("returned: OK");
    }

    @Test
    void testExceptionLogs(CapturedOutput output) {
        try {
            testService.failMethod();
        } catch (Exception ignored) {}

        assertThat(output.getOut())
                .contains("Entering: failMethod")
                .contains("Exception in: failMethod")
                .contains("Exiting: failMethod");
    }
}