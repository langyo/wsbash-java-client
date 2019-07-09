# WebSocket Bash

## (Java Client)

[中文](README.md) | [English](README_eng.md)

This is a communication tool library based on the WebSocket protocol, which allows developers to easily and quickly complete the communication between the client and the server, and only pay attention to the specific data processing logic.

This tool library allows you to write in multiple languages, and the clients/servers written by this library can be directly docked between different languages.

Another libraries：

|      Language       |  Type  |                           Location                           |
| :-----------------: | :----: | :----------------------------------------------------------: |
| JavaScript(Node.js) | Server | [wsbash-node-server](https://github.com/mcbbs-developer/wsbash-node-server) |
|  JavaScript(HTML5)  | Client | [wsbash-h5-client](https://github.com/mcbbs-developer/wsbash-h5-client) |

## concept

The communication between the client and the server is based on the text stream communication provided by the WebSocket protocol. Just like the command line, after establishing communication with the server, the client can call each other's registered "command" by sending texts similar to the command line.

Text like this will be sent between them:

```shell
# Sent by the client, you need to register first, and then the connection will succeed.
execute system register h5

# Replyed by the server, prompting the registration to succeed,
# and providing the ID of the connection established by this client (similar to session ID).
data system register ok jCsi1m9z

# Sent by the client, the simulation requests some data;
# "database get" is the developer registered command,
# "langyo money" is the parameter passed to the function registered by the instruction.
execute database get langyo money

# Replyed by the server, simulates the function corresponding to the instruction,
# reads the database, and replies with a number;
# When replying, it automatically fills in the type of the sent instruction and the execution status (success/fail),
# so that the client can correctly identify.
data database get success no-money
```