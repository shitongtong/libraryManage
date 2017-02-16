package com.stt.websocket.tomcat.snack;

import com.stt.websocket.tomcat.snake.Snake;
import com.stt.websocket.tomcat.snake.SnakeTimer;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;

/**
 * Created by Administrator on 2017-02-16.
 *
 * @author shitongtong
 */
public class SnakeTimerTests {

    @Test
    public void removeDysfunctionalSnakes() throws Exception {
        Snake snake = mock(Snake.class);
        willThrow(new IOException()).given(snake).sendMessage(anyString());
        SnakeTimer.addSnack(snake);

        SnakeTimer.broadcast("");
        assertThat(SnakeTimer.getSnakes()).hasSize(0);
    }
}
