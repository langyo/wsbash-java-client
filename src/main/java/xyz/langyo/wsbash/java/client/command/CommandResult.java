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

import xyz.langyo.wsbash.java.client.command.CommandResultType;
import java.util.List;
import java.util.Arrays;

/**
 * 执行结果与理由的包装类
 *
 * @param <R> 命令执行结果的参数类型
 * @author yinyangshi InitAuther97 langyo
 */
public class CommandResult<R> {
    private final CommandResultType type;
    private final List<? extends R> result;

    /**
     * @param type 命令结果类型
     * @param result 命令返回的结果参数
     */
    public CommandResult(CommandResultType type, R... result) {
        this.type = type;
        this.result = Arrays.asList(result);
    }

    /**
     * 获得命令结果Type
     * @return 命令类型
     */
    public CommandResultType getType() {
        return type;
    }

    /**
     * 获得命令结果的返回值
     * @return 命令执行结果
     */
    public List<? extends R> getResult() {
        return result;
    }
}
