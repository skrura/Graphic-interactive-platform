package com.yh.schedule.service.impl;

import com.yh.model.schedule.dtos.Task;
import com.yh.schedule.ScheduleApplication;
import com.yh.schedule.service.TaskService;
import lombok.Data;
import org.bouncycastle.util.test.FixedSecureRandom;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = ScheduleApplication.class)
@RunWith(SpringRunner.class)
class TaskServiceImplTest {
    @Autowired
    private TaskService taskService;

    @Test
    void addTask() {

        Task task = new Task();
        task.setTaskType(100);
        task.setPriority(50);
        task.setParameters("task test".getBytes());
        task.setExecuteTime(new Date().getTime()+100000);

        long taskId = taskService.addTask(task);

        System.out.println(taskId);

    }

    @Test
    void cancelTask() {
        taskService.cancelTask(1688190653407690754L);
    }
}