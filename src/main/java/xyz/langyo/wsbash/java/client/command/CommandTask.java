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

import xyz.langyo.wsbash.java.client.command.CommandResult;
import xyz.langyo.wsbash.java.client.command.Command;
import xyz.langyo.wsbash.java.client.util.Callback;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CommandTask extends FutureTask<CommandResult> {
    private final Command command;
    private final Set<Callback<Map<String,Object>>> callbacks = Sets.newHashSet();
    private final UUID taskId;

    public CommandTask(Command cmd, UUID taskId){
        super(()-> Client.getCommandManager().require(cmd.getPkgName(),cmd.getNamespace()).childCommand(cmd.getMethod()).execute(cmd.getArgs()));
        this.command = cmd;
        this.taskId=taskId;
    }

    public Command getCommand() {
        return command;
    }

    public CommandTask callback(Callback<Map<String,Object>> callback){
        callbacks.add(callback);
        return this;
    }

    @Override
    public void run() {
        super.run();
        callbacks.forEach(cb -> {
            try {
                Map<String,Object> map = Maps.newHashMap();
                map.put("result",get());
                map.put("taskId",taskId);
                map.put("command",command);
                cb.callback(map);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    public UUID getTaskId() {
        return taskId;
    }
}
