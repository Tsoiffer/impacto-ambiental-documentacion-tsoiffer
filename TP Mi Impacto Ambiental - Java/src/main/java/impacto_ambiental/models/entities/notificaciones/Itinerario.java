package impacto_ambiental.models.entities.notificaciones;


import org.quartz.*;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;

public class Itinerario {

  public static void main(String args[]) throws SchedulerException {
    SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

    Scheduler sched = schedFact.getScheduler();




    CronTrigger trigger = newTrigger()
        .withIdentity("trigger1", "group1")
        .withSchedule(cronSchedule("0/15 * * * * ? *")) //0 15 10 1 * ?
          .forJob("job1", "group1")
            .startNow()
        .build();

    JobDetail job = newJob(ObservadorRecomendaciones.class)
        .withIdentity("job1", "group1") // name "myJob", group "group1"
        .build();

    sched.start();

    sched.scheduleJob(job, trigger);



    System.out.println("Sched OK");

    try {
      Thread.sleep(5L * 1000L);
      System.out.println("5s transcurridos");
      Thread.sleep(5L * 1000L);
      System.out.println("10s transcurridos");
      Thread.sleep(5L * 1000L);
      System.out.println("15s transcurridos");

    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    sched.shutdown(true);
    System.out.println("Apagando...");
  }




}