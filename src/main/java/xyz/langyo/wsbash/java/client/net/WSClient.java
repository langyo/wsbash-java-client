/*
   Copyright 2019 langyo<langyo.china@gmail.com> and contributors

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

package xyz.langyo.wsbash.java.client.net;

import xyz.langyo.wsbash.java.client.command.CommandDispatcher;
import xyz.langyo.wsbash.java.client.command.CommandParser;
import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 * Single Client
 *
 * @author Jiji_Sasidharan langyo
 */
@ClientEndpoint
public class WSClient {
    private Session userSession = null;
    
    public WSClient(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 连接建立的回调函数
     *
     * @param userSession 打开连接时建立的句柄
     */
    @OnOpen
    public void onOpen(Session userSession) {
        this.userSession = userSession;
    }

    /**
     * 连接关闭的回调函数
     *
     * @param userSession 关闭的连接
     * @param reason 关闭连接的理由
     */
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println(reason.toString());
        this.userSession = null;
    }

    /**
     * 接收到消息的回调函数
     *
     * @param message 接收到的消息
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println(message);
    }
    
    /**
     * 发送消息
     *
     * @param message 消息
     */
    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }
}