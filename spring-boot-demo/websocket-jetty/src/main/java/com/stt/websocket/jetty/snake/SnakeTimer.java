package com.stt.websocket.jetty.snake;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Sets up the timer for the multi-player snake game WebSocket example.
 *
 * Created by Administrator on 2017-02-16.
 *
 * @author shitongtong
 */
public class SnakeTimer {

    private static final long TICK_DELAY = 100;

    private static final Object MONITOR = new Object();

    private static final Log log = LogFactory.getLog(SnakeTimer.class);

    private static final ConcurrentHashMap<Integer, Snake> snakes = new ConcurrentHashMap<Integer, Snake>();

    private static Timer gameTimer = null;

    public static void addSnack(Snake snake) {
        synchronized (MONITOR) {
            if (snakes.isEmpty()){
                startTimer();
            }
            snakes.put(Integer.valueOf(snake.getId()), snake);
        }
    }

    public static void startTimer(){
        gameTimer = new Timer(SnakeTimer.class.getSimpleName()+" Timer");
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    tick();
                } catch (IOException e) {
                    log.error("Caught to prevent timer from shutting down", e);
                    e.printStackTrace();
                }
            }
        },TICK_DELAY,TICK_DELAY);
    }

    public static void tick() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Iterator<Snake> iterator = SnakeTimer.getSnakes().iterator(); iterator.hasNext();){
            Snake snake = iterator.next();
            snake.update(SnakeTimer.getSnakes());
            sb.append(snake.getLocationsJson());
            if (iterator.hasNext()){
                sb.append(",");
            }
        }

        broadcast(String.format("{'type': 'update', 'data' : [%s]}", sb.toString()));

    }

    public static void broadcast(String message){
        Collection<Snake> snakes = new CopyOnWriteArrayList<>(SnakeTimer.getSnakes());
        for (Snake snake : snakes) {
            try {
                snake.sendMessage(message);
            }
            catch (Throwable ex) {
                // if Snake#sendMessage fails the client is removed
                removeSnake(snake);
            }
        }
    }

    public static void removeSnake(Snake snake){
        synchronized (MONITOR){
            snakes.remove(Integer.valueOf(snake.getId()));
            if (snakes.isEmpty()){
                stopTimer();
            }
        }
    }

    public static void stopTimer(){
        if (gameTimer != null){
            gameTimer.cancel();
        }
    }

    public static Collection<Snake> getSnakes(){
        return Collections.unmodifiableCollection(snakes.values());
    }
}
