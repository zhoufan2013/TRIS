package com.ai.core;

import com.codahale.metrics.ConsoleReporter;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import org.seleniumhq.selenium.fluent.monitors.CodaHaleMetricsMonitor;

import java.util.concurrent.TimeUnit;

/**
 * 引入监控指标类库@url{https://dropwizard.github.io/metrics/3.1.0/}
 * Created by zhoufan on 15/4/30.
 */
public class WholeSuiteListener extends RunListener{

    @Override
    public void testRunFinished(Result result) throws Exception {
        super.testRunFinished(result);

        final ConsoleReporter reporter = ConsoleReporter.forRegistry(codahaleMetricsMonitor.getMetrics())
                .convertRatesTo(TimeUnit.MILLISECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .outputTo(System.out)
                .build();

        reporter.report();
    }

    public static final CodaHaleMetricsMonitor codahaleMetricsMonitor = new CodaHaleMetricsMonitor("com.ai.");
}
