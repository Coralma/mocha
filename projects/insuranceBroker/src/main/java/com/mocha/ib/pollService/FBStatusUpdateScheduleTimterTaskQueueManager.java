package com.mocha.ib.pollService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FBStatusUpdateScheduleTimterTaskQueueManager extends ThreadPoolExecutor {

  class TaskQueue {
    BlockingQueue<FBStatusUpdatechedulerTask> taskQueue =
        new LinkedBlockingDeque<FBStatusUpdatechedulerTask>();

    public void taskQueueProducer(FBStatusUpdatechedulerTask task) throws InterruptedException {
      taskQueue.put(task);
    }

    public FBStatusUpdatechedulerTask taskQueueConsumer() throws InterruptedException {
      return taskQueue.take();
    }
  }

  public FBStatusUpdateScheduleTimterTaskQueueManager(int corePoolSize, int maximumPoolSize,
      long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
  }

  @Override
  protected void beforeExecute(Thread t, Runnable r) {
    super.beforeExecute(t, r);
    System.out.println("Perform beforeExecute() logic");
  }

  @Override
  protected void afterExecute(Runnable r, Throwable t) {
    super.afterExecute(r, t);
    if (t != null) {
      System.out.println("Perform exception handler logic");
    }
    System.out.println("Perform afterExecute() logic");
  }

  class TaskQueueHandler {

    TaskQueue taskQueue = new TaskQueue();

    public TaskQueueHandler() {

    }

    public class TaskQueueConsumerHandler implements Runnable {
      @Override
      public void run() {
        try {
          System.out.println("Execute queue task");
          taskQueue.taskQueueConsumer();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String args[]) {
    Integer threadCounter = 0;
    BlockingQueue<Runnable> blockingQueue = new LinkedBlockingDeque<Runnable>();
    FBStatusUpdateScheduleTimterTaskQueueManager executor =
        new FBStatusUpdateScheduleTimterTaskQueueManager(10, 20, 5000, TimeUnit.MILLISECONDS,
            blockingQueue);
    executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
      @Override
      public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Lets add another time : ");
        executor.execute(r);
      }
    });
    // Let start all core threads initially
    executor.prestartAllCoreThreads();
    while (true) {
      threadCounter++;
      // Adding threads one by one
      FBStatusUpdatechedulerTask demoTask = new FBStatusUpdatechedulerTask(null);
      // demoTask.run();
      // // if (threadCounter == 100) {
      // // System.out.println("Stop Demo Task Now!");
      // // break;
      // // }
      executor.execute(demoTask);
    }

    // for (int i = 0; i < 10; i++) {
    // System.out.println("Total task number " + blockingQueue.size());
    // blockingQueue.add(new Runnable() {
    // @Override
    // public void run() {
    // FBStatusUpdatechedulerTask demoTask = new LinkedinScheduleTimerTask();
    // demoTask.setRunInternval(1000 * 20);
    // demoTask.setName("vanceTestName");
    // demoTask.run();
    // // System.out.println("Start run the backend job " + demoTask.getName() + " at : " +
    // Calendar.getInstance().getTime());
    // }
    // });
    // }
  }
}
