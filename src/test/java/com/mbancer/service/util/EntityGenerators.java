package com.mbancer.service.util;

import com.mbancer.domain.Project;
import com.mbancer.domain.Task;
import com.mbancer.domain.User;
import org.apache.commons.lang.RandomStringUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EntityGenerators {

    public static Set<Task> generateTasks(final int number, final User user, final Project project){
        final Set<Task> tasks = new HashSet<>();
        for(int i = 0 ; i < number ; i++){
            tasks.add(
                Task.builder()
                    .user(user)
                    .created(LocalDate.now())
                    .description(RandomStringUtils.randomAlphabetic(10))
                    .project(project)
                    .title(RandomStringUtils.randomAlphabetic(10))
                    .build()
            );
        }
        return tasks;
    }

    public static Set<Project> generateProjects(final int number, final Set<User> users, final Set<Task> tasks){
        final Set<Project> projects = new HashSet<>();
        final Project.Builder builder = Project.builder();

        for(int i = 0 ; i < number ; i++){
            final Project project = builder
                .name(RandomStringUtils.randomAlphabetic(10))
                .description(RandomStringUtils.randomAlphabetic(10))
                .created(LocalDate.now())
                .deadLine(LocalDate.now().plus(2, ChronoUnit.YEARS))
                .build();

            if(users != null){
                project.setUsers(users);
            }
            if(tasks != null){
                project.setTasks(tasks);
            }

            projects.add(project);
        }
        return projects;
    }

    public static Task generateTask(final User user, final Project project){
        return generateTasks(1, user, project).iterator().next();
    }

    public static Project generateProject(final Set<User> users, final Set<Task> tasks){
        return generateProjects(1, users, tasks).iterator().next();
    }
}