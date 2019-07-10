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

import java.util.List;

public class CommandParser {
    public static List<String> parse(String command){
        List<String> parts = new List<String>();
        for(int i = command.indexOf(" "), lastI = i; i != -1; lastI = i + 1, i = command.indexOf(" ", i)) {
            parts.add(command.substring(lastI, i));
        }
    }
    public static String format(Command command) {
       return command.toString();
    }
}
