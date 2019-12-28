package com.ledinhtuyenbkdn.masterpersonindex.runner;

import com.ledinhtuyenbkdn.masterpersonindex.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

//@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private DashboardService dashboardService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(dashboardService.getPersonStatistic());
    }
}
