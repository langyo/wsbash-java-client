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

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import xyz.langyo.wsbash.java.client.command.*;

public class Command {
    public final CommandType type;
    public final CommandResultType state;
    public final List<String> methodPath;
    public final List<String> args;

    public Command(String cmd){
        List<String> parts = CommandParser.parse(cmd);
        switch(parts.get(0)) {
            case EXECUTE:
                methodPath = CommandMethodPool.getCurrentExecuteMethodPath(parts.subList(1, parts.size()));
                args = parts.subList(methodPath.size() + 1, parts.size());
                break;
            case DATA:
                methodPath = CommandMethodPool.getCurrentDataMethodPath(parts.subList(1, parts.size()));
                args = parts.subList(methodPath.size() + 2, parts.size());
                state = parts.get(methodPath.size() + 1);
                break;
            default:
                throw new Exception("Unknown command!");
        }

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        for(String key:methodPath) {
            sb.append(" ").append(key);
        }
        if(type == CommandType.DATA) sb.append(" ").append(state);
        for(String key:args) {
            sb.append(" ").append(key);
        }
        return sb.toString();
    }
}
