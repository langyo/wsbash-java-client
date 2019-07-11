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

package xyz.langyo.wsbash.java.client.command;

import xyz.langyo.wsbash.java.client.command.Command;
import xyz.langyo.wsbash.java.client.command.CommandTask;
import xyz.langyo.wsbash.java.client.command.CommandType;
import xyz.langyo.wsbash.java.client.command.CommandResult;
import xyz.langyo.wsbash.java.client.command.CommandResultType;
import xyz.langyo.wsbash.java.client.net.WSClient;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class CommandDispatcher {
    public static final ExecutorService SERVICE = Executors.newFixedThreadPool(16);
    public static final List<CommandTask> TASKS = Lists.newArrayList();

    private CommandDispatcher() { }

    public final void dispatchAsync(Command command) {
        switch (command.getType()) {
            case CommandType.EXECUTE:
                CommandTask ct = new CommandTask(command,UUID.randomUUID()).callback(arg -> {
                    CommandResult result = (CommandResult) arg.get("result");
                    UUID taskid = (UUID) arg.get("taskId");
                    JsonObject jsonObject = ((Command) arg.get("command")).asGJson();
                    JsonArray array = jsonObject.getAsJsonArray("args");
                    array.add("id="+UUID.randomUUID());
                    for(Object thing:result.getResult()){
                        array.add(thing.toString());
                    }
                    jsonObject.remove("args");
                    jsonObject.add("args", array);
                    WSClient.INSTANCE.send(jsonObject.toString());
                    TASKS.stream().filter(task->task.getTaskId().equals(taskid)).findAny().ifPresent(TASKS::remove);
                });
                TASKS.add(ct);
                SERVICE.submit(ct);
                break;
            case CommandType.DATA:
                break;
            default: {
                break;
            }
        }
    }

    public final void send(Command command) {
        WSClient.send(command.toString());
    }
}
