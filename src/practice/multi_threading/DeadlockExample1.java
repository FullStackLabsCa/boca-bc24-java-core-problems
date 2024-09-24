package practice.multi_threading;

import java.awt.print.Paper;

public class DeadlockExample1 {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread thread1 = new Thread(new Task1(pen, paper), "Thread-1");
        Thread thread2 = new Thread(new Task2(pen, paper), "Thread-2");

        thread1.start();
        thread2.start();

    }


    static class Pen {
        public synchronized void writeWithPenAndPaper(Paper paper) {
            System.out.println(Thread.currentThread().getName() + " is using pen " + this + " and trying to get paper");
            paper.finishWriting();
        }

        public synchronized void finishWriting() {
            System.out.println(Thread.currentThread().getName() + " finished using Pen " + this);
        }
    }

    static class Paper {
        public synchronized void writeWithPenAndPaper(Pen pen) {
            System.out.println(Thread.currentThread().getName() + " is using paper " + this + "and trying to get pen");
            pen.finishWriting();
        }

        public synchronized void finishWriting() {
            System.out.println(Thread.currentThread().getName() + " finished using Paper" + this);
        }
    }

    static class Task1 implements Runnable {
        private Pen pen;
        private Paper paper;

        public Task1(Pen pen, Paper paper) {
            this.pen = pen;
            this.paper = paper;
        }

        @Override
        public void run() {
            pen.writeWithPenAndPaper(paper); // thread1 locks pen and tries to lock paper
        }
    }

    static class Task2 implements Runnable {
        private Pen pen;
        private Paper paper;

        public Task2(Pen pen, Paper paper) {
            this.pen = pen;
            this.paper = paper;
        }

        @Override
        public void run() {
            synchronized (pen) {
                paper.writeWithPenAndPaper(pen); // thread2 locks paper and tries to lock pen
            }
        }
    }


}
